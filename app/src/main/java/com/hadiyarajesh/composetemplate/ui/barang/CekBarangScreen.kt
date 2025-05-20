package com.hadiyarajesh.composetemplate.ui.barang

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
<<<<<<< HEAD
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
=======
>>>>>>> 5ec21fad4d76a3fc50c0c1c8f014bbf8ce8481a2
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab

data class BarangLab(
    val nama: String,
    val kategori: String,
    val kondisi: String,
    val labtekId: String,
    val pengelolaId: String,
    val status: String,
    val tanggalMasuk: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarangTable(barangList: List<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Barang") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
<<<<<<< HEAD
            .background(Color(0xFFF5F7FA)) // Warna latar belakang lebih lembut
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
                .padding(
                    top = innerPadding.calculateTopPadding(), // Menghormati TopAppBar
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column {
                    // Header Tabel
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.9f))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nama",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Kategori",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Kondisi",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Labtek ID",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Pengelola ID",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Status",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Tanggal",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Divider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                        thickness = 1.dp
                    )

                    // Isi Tabel
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(barangList) { barang ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { /* Handle row click */ }
                                    .background(
                                        if (barangList.indexOf(barang) % 2 == 0) {
                                            MaterialTheme.colorScheme.surface
                                        } else {
                                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        }
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = barang.nama,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = barang.kategori,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = barang.kondisi,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = barang.labtekId,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = barang.pengelolaId,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                StatusBadge(
                                    status = barang.status,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = barang.tanggalMasuk,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Divider(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                                thickness = 0.5.dp
                            )
                        }
                    }
=======
            .padding(
                top = 56.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
//            .height(1.dp)
    ) {
        // Header Tabel


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp)
        )
        {

            Text("Nama", Modifier.weight(1f), color = Color.White)
            Text("Kategori", Modifier.weight(1f), color = Color.White)
            Text("Kondisi", Modifier.weight(1f), color = Color.White)
            Text("Labtek ID", Modifier.weight(1f), color = Color.White)
            Text("Pengelola ID", Modifier.weight(1f), color = Color.White)
            Text("Status", Modifier.weight(1f), color = Color.White)
            Text("Tanggal", Modifier.weight(1f), color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Isi Tabel
        LazyColumn {
            items(barangList) { barang ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(barang.nama, Modifier.weight(1f))
                    Text(barang.kategori, Modifier.weight(1f))
                    Text(barang.kondisi, Modifier.weight(1f))
                    Text(barang.labtekId, Modifier.weight(1f))
                    Text(barang.pengelolaId, Modifier.weight(1f))
                    Text(barang.status, Modifier.weight(1f))
                    Text(barang.tanggalMasuk, Modifier.weight(1f))
>>>>>>> 5ec21fad4d76a3fc50c0c1c8f014bbf8ce8481a2
                }
                Divider()
            }
        }
    }
}

@Composable
fun StatusBadge(status: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(horizontal = 4.dp),
        shape = MaterialTheme.shapes.small,
        color = when (status) {
            "Aktif" -> Color(0xFF4CAF50).copy(alpha = 0.2f) // Hijau untuk Aktif
            "Nonaktif" -> Color(0xFFF44336).copy(alpha = 0.2f) // Merah untuk Nonaktif
            else -> MaterialTheme.colorScheme.surfaceVariant
        }
    ) {
        Text(
            text = status,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
            color = when (status) {
                "Aktif" -> Color(0xFF4CAF50)
                "Nonaktif" -> Color(0xFFF44336)
                else -> MaterialTheme.colorScheme.onSurface
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BarangTablePreview() {
//    val asd = listOf(
//        BarangLab("Laptop", "Elektronik", "Baik", "LT01", "PG01", "Aktif", "18/05/2025"),
//        BarangLab("Proyektor", "Elektronik", "Perlu Perbaikan", "LT02", "PG02", "Nonaktif", "10/04/2024"),
//        BarangLab("Meja", "Furnitur", "Baik", "LT03", "PG03", "Aktif", "05/03/2023")
//    )
    BarangTable(
        barangList = listOf(
            BarangLab("Laptop", "Elektronik", "Baik", "LT01", "PG01", "Aktif", "18/05/2025"),
            BarangLab("Proyektor", "Elektronik", "Perlu Perbaikan", "LT02", "PG02", "Nonaktif", "10/04/2024"),
            BarangLab("Meja", "Furnitur", "Baik", "LT03", "PG03", "Aktif", "05/03/2023")
        )
    )
}
