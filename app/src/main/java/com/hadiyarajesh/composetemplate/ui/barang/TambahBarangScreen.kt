package com.hadiyarajesh.composetemplate.ui.barang

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hadiyarajesh.composetemplate.ui.barang.BarangViewModel
import com.hadiyarajesh.composetemplate.ui.barang.BarangLab

@Composable
fun TambahBarangScreen(
    barangViewModel: BarangViewModel = viewModel()
) {
    val uiState by barangViewModel.uiState.collectAsState()

    var namaBarang by remember { mutableStateOf("") }
    var kondisiExpanded by remember { mutableStateOf(false) }
    var kondisiSelected by remember { mutableStateOf("Pilih Kondisi") }
    val kondisiOptions = listOf("Baik", "Perlu Perbaikan", "Rusak")

    var lokasiBarang by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tambah Barang",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = namaBarang,
            onValueChange = { namaBarang = it },
            label = { Text("Nama Barang") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = kondisiExpanded,
            onExpandedChange = { kondisiExpanded = !kondisiExpanded }
        ) {
            OutlinedTextField(
                value = kondisiSelected,
                onValueChange = {},
                readOnly = true,
                label = { Text("Kondisi Barang") },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = kondisiExpanded,
                onDismissRequest = { kondisiExpanded = false }
            ) {
                kondisiOptions.forEach { opsi ->
                    DropdownMenuItem(
                        text = { Text(opsi) },
                        onClick = {
                            kondisiSelected = opsi
                            kondisiExpanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = lokasiBarang,
            onValueChange = { lokasiBarang = it },
            label = { Text("ID Barang") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (namaBarang.isNotBlank() && kondisiSelected != "Pilih Kondisi" && lokasiBarang.isNotBlank()) {
                    val newBarang = BarangLab(
                        id = lokasiBarang,
                        nama = namaBarang,
                        kondisi = kondisiSelected,
                        lokasi = lokasiBarang
                    )
                    barangViewModel.tambahBarang(newBarang)
                    Toast.makeText(context, "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    // Reset fields
                    namaBarang = ""
                    kondisiSelected = "Pilih Kondisi"
                    lokasiBarang = ""
                } else {
                    Toast.makeText(context, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TambahBarangScreenPreview() {
    MaterialTheme {
        TambahBarangScreen(
            onSimpan = { nama, kondisi, lokasi ->
                // Dummy action, bisa diabaikan saat preview
            }
        )
    }
}

