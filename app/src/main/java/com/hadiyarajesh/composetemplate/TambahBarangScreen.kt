package com.hadiyarajesh.composetemplate

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
@Composable
fun TambahBarangScreen(
    onSimpan: (String, String, String) -> Unit
) {
    val context = LocalContext.current

    var namaBarang by remember { mutableStateOf("") }
    var kondisiExpanded by remember { mutableStateOf(false) }
    var kondisiSelected by remember { mutableStateOf("Pilih Kondisi") }
    val kondisiOptions = listOf("Baik", "Perlu Perbaikan", "Rusak")

    var lokasiBarang by remember { mutableStateOf("") }

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
                    onSimpan(namaBarang, kondisiSelected, lokasiBarang)
                    Toast.makeText(context, "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show()
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

