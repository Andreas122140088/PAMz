package com.hadiyarajesh.composetemplate.ui.barang

import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
// Hapus import FirebaseAuth jika error unresolved reference
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import android.widget.Toast
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun BarangTable(
    barangList: List<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab>,
    onEdit: (com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab) -> Unit = {},
    onDelete: (com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab) -> Unit = {}
) {
    Scaffold(
        containerColor = Color(0xFFE3F2FD) // Biru muda untuk background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (barangList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Tidak ada data barang.", color = Color(0xFF1565C0))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(barangList) { barang ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(2.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(barang.nama, style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF000000), fontWeight = FontWeight.Bold))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Kategori: ${barang.kategori}", color = Color(0xFF000000))
                                Text("Kondisi: ${barang.kondisi}", color = Color(0xFF000000))
                                Text("Status: ${barang.status}", color = Color(0xFF000000))
                                Text("Tanggal Masuk: ${barang.tanggalMasuk}", color = Color(0xFF000000))
                                Text("ID: ${barang.id}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    IconButton(onClick = { onEdit(barang) }) {
                                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color(0xFF1976D2))
                                    }
                                    IconButton(onClick = { onDelete(barang) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Hapus", tint = Color(0xFFD32F2F))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CekBarangScreen() {
    val barangFlow = remember { BarangRepository.listenBarangList() }
    val barangList by barangFlow.collectAsState(initial = emptyList())
    val currentUser = FirebaseAuth.getInstance().currentUser
    val isAuthorized = currentUser?.email == "labtek1@gmail.com"
    val filteredList = if (isAuthorized) {
        barangList.filter { it.pengelolaId == currentUser?.email }
    } else {
        emptyList()
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val barangToDeleteState = remember { mutableStateOf<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab?>(null) }
    val barangToEditState = remember { mutableStateOf<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab?>(null) }
    val barangToDelete = barangToDeleteState.value
    val setBarangToDelete = { b: com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab? -> barangToDeleteState.value = b }
    val barangToEdit = barangToEditState.value
    val setBarangToEdit = { b: com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab? -> barangToEditState.value = b }

    // Tambahkan notifikasi jika filteredList kosong karena pengelolaId tidak cocok
    if (isAuthorized && barangList.isNotEmpty() && filteredList.isEmpty()) {
        Toast.makeText(context, "Tidak ada barang dengan pengelolaId: ${currentUser?.email}", Toast.LENGTH_LONG).show()
    }

    // Dialog konfirmasi hapus
    if (barangToDelete != null && isAuthorized) {
        AlertDialog(
            onDismissRequest = { setBarangToDelete(null) },
            title = { Text("Konfirmasi Hapus") },
            text = { Text("Apakah Anda yakin ingin menghapus barang '${barangToDelete.nama}'? (id: ${barangToDelete.id})") },
            confirmButton = {
                TextButton(onClick = {
                    coroutineScope.launch {
                        Toast.makeText(context, "Memulai hapus id: ${barangToDelete.id}", Toast.LENGTH_SHORT).show()
                        try {
                            BarangRepository.hapusBarang(barangToDelete)
                            Toast.makeText(context, "Barang berhasil dihapus (id: ${barangToDelete.id})", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Gagal menghapus barang: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                        setBarangToDelete(null)
                    }
                }) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                TextButton(onClick = { setBarangToDelete(null) }) {
                    Text("Batal")
                }
            }
        )
    }

    // Dialog edit dengan form
    if (barangToEdit != null && isAuthorized) {
        // Gunakan rememberUpdatedState agar data edit selalu sinkron dengan barangToEdit terbaru
        val namaState = rememberUpdatedState(barangToEdit.nama)
        val kategoriState = rememberUpdatedState(barangToEdit.kategori)
        val kondisiState = rememberUpdatedState(barangToEdit.kondisi)
        val statusState = rememberUpdatedState(barangToEdit.status)
        val tanggalMasukState = rememberUpdatedState(barangToEdit.tanggalMasuk)
        var nama by remember { mutableStateOf(namaState.value) }
        var kategori by remember { mutableStateOf(kategoriState.value) }
        var kondisi by remember { mutableStateOf(kondisiState.value) }
        var status by remember { mutableStateOf(statusState.value) }
        var tanggalMasuk by remember { mutableStateOf(tanggalMasukState.value) }
        AlertDialog(
            onDismissRequest = { setBarangToEdit(null) },
            title = { Text("Edit Barang") },
            text = {
                Column {
                    OutlinedTextField(
                        value = nama,
                        onValueChange = { nama = it },
                        label = { Text("Nama") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = kategori,
                        onValueChange = { kategori = it },
                        label = { Text("Kategori") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = kondisi,
                        onValueChange = { kondisi = it },
                        label = { Text("Kondisi") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = status,
                        onValueChange = { status = it },
                        label = { Text("Status") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = tanggalMasuk,
                        onValueChange = { tanggalMasuk = it },
                        label = { Text("Tanggal Masuk") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    coroutineScope.launch {
                        Toast.makeText(context, "Memulai update id: ${barangToEdit.id}", Toast.LENGTH_SHORT).show()
                        try {
                            BarangRepository.updateBarang(
                                barangToEdit.copy(
                                    nama = nama,
                                    kategori = kategori,
                                    kondisi = kondisi,
                                    status = status,
                                    tanggalMasuk = tanggalMasuk
                                )
                            )
                            Toast.makeText(context, "Barang berhasil diupdate (id: ${barangToEdit.id})", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Gagal update barang: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                        setBarangToEdit(null)
                    }
                }) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { setBarangToEdit(null) }) {
                    Text("Batal")
                }
            }
        )
    }

    BarangTable(
        barangList = filteredList,
        onEdit = setBarangToEdit,
        onDelete = setBarangToDelete
    )
    if (!isAuthorized) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Anda tidak memiliki akses edit/hapus.", color = Color.Red)
        }
    }
}

//@Composable
//fun StatusBadge(status: String, modifier: Modifier = Modifier) {
//    Surface(
//        modifier = modifier.padding(horizontal = 4.dp),
//        shape = MaterialTheme.shapes.small,
//        color = when (status) {
//            "Aktif" -> Color(0xFF4CAF50).copy(alpha = 0.2f) // Hijau untuk Aktif
//            "Nonaktif" -> Color(0xFFF44336).copy(alpha = 0.2f) // Merah untuk Nonaktif
//            else -> MaterialTheme.colorScheme.surfaceVariant
//        }
//    ) {
//        Text(
//            text = status,
//            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
//            style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
//            color = when (status) {
//                "Aktif" -> Color(0xFF4CAF50)
//                "Nonaktif" -> Color(0xFFF44336)
//                else -> MaterialTheme.colorScheme.onSurface
//            },
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun BarangTablePreview() {
    BarangTable(
        barangList = listOf(
            com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab("Laptop", "Elektronik", "Baik", "LT01", "PG01", "Aktif", "18/05/2025"),
            com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab("Proyektor", "Elektronik", "Perlu Perbaikan", "LT02", "PG02", "Nonaktif", "10/04/2024"),
            com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab("Meja", "Furnitur", "Baik", "LT03", "PG03", "Aktif", "05/03/2023")
        )
    )
}
