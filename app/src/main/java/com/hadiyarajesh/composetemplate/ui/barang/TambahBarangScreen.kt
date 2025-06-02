package com.hadiyarajesh.composetemplate.ui.barang

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahBarangScreen(
    onSimpan: (String, String, String, String, String, String, String) -> Unit
) {
    val context = LocalContext.current

    var namaBarang by remember { mutableStateOf("") }
    var kategoriBarang by remember { mutableStateOf("") }
    var kondisiExpanded by remember { mutableStateOf(false) }
    var kondisiSelected by remember { mutableStateOf("Pilih Kondisi") }
    val kondisiOptions = listOf("Baik", "Perlu Perbaikan", "Rusak")
    var labtekId by remember { mutableStateOf("") }
    var pengelolaId by remember { mutableStateOf("") }
    var statusExpanded by remember { mutableStateOf(false) }
    var statusSelected by remember { mutableStateOf("Pilih Status") }
    val statusOptions = listOf("Aktif", "Nonaktif")
    var tanggalMasuk by remember { mutableStateOf("") }

    // State untuk DatePicker
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    // Format tanggal
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Hilangkan isError dan border merah pada semua input
    // Ganti background utama menjadi biru muda seperti SettingPage
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Tambah Barang")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1E88E5),
                    titleContentColor = Color.White
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Biru muda seperti SettingPage
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(
                    top = innerPadding.calculateTopPadding(), // Respect TopAppBar
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Silakan isi data barang dengan lengkap",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF1976D2)),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), // Slightly higher elevation
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp), // Increased spacing
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = namaBarang,
                        onValueChange = { namaBarang = it },
                        label = { Text("Nama Barang") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF757575)
                        )
                    )

                    OutlinedTextField(
                        value = kategoriBarang,
                        onValueChange = { kategoriBarang = it },
                        label = { Text("Kategori Barang") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF757575)
                        )
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
                                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1E88E5),
                                unfocusedBorderColor = Color(0xFF757575)
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = kondisiExpanded,
                            onDismissRequest = { kondisiExpanded = false },
                            modifier = Modifier.background(Color.White)
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
                        value = labtekId,
                        onValueChange = { labtekId = it },
                        label = { Text("Labtek ID") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF757575)
                        )
                    )

                    OutlinedTextField(
                        value = pengelolaId,
                        onValueChange = { pengelolaId = it },
                        label = { Text("Pengelola ID") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF757575)
                        )
                    )

                    ExposedDropdownMenuBox(
                        expanded = statusExpanded,
                        onExpandedChange = { statusExpanded = !statusExpanded }
                    ) {
                        OutlinedTextField(
                            value = statusSelected,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Status") },
                            trailingIcon = {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1E88E5),
                                unfocusedBorderColor = Color(0xFF757575)
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = statusExpanded,
                            onDismissRequest = { statusExpanded = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            statusOptions.forEach { opsi ->
                                DropdownMenuItem(
                                    text = { Text(opsi) },
                                    onClick = {
                                        statusSelected = opsi
                                        statusExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = tanggalMasuk,
                        onValueChange = { /* Tidak diizinkan input manual */ },
                        label = { Text("Tanggal Masuk") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .padding(bottom = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "Pilih Tanggal")
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF757575)
                        )
                    )

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        datePickerState.selectedDateMillis?.let { millis ->
                                            tanggalMasuk = dateFormatter.format(Date(millis))
                                        }
                                        showDatePicker = false
                                    }
                                ) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDatePicker = false }) {
                                    Text("Cancel")
                                }
                            },
                            modifier = Modifier.background(Color(0xFFE3F2FD)) // Consistent dialog background
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp)) // Increased spacing

                    val isFormValid = namaBarang.isNotBlank() && kategoriBarang.isNotBlank() && kondisiSelected != "Pilih Kondisi" && labtekId.isNotBlank() && pengelolaId.isNotBlank() && statusSelected != "Pilih Status" && tanggalMasuk.isNotBlank()
                    Button(
                        onClick = {
                            onSimpan(
                                namaBarang,
                                kategoriBarang,
                                kondisiSelected,
                                labtekId,
                                pengelolaId,
                                statusSelected,
                                tanggalMasuk
                            )
                            Toast.makeText(context, "Barang berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                            namaBarang = ""
                            kategoriBarang = ""
                            kondisiSelected = "Pilih Kondisi"
                            labtekId = ""
                            pengelolaId = ""
                            statusSelected = "Pilih Status"
                            tanggalMasuk = ""
                        },
                        enabled = isFormValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                    ) {
                        Text("Simpan", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TambahBarangScreenPreview() {
    TambahBarangScreen(
        onSimpan = { _, _, _, _, _, _, _ -> }
    )
}

