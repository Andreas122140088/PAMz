package com.hadiyarajesh.composetemplate.ui.barang

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
<<<<<<< HEAD
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
=======
>>>>>>> 5ec21fad4d76a3fc50c0c1c8f014bbf8ce8481a2
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.hadiyarajesh.composetemplate.ui.theme.MyAppTheme
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
    var status by remember { mutableStateOf("") }
    var tanggalMasuk by remember { mutableStateOf("") }

    // State untuk DatePicker
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    // Format tanggal
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

<<<<<<< HEAD
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Barang") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA)) // Softer background color
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
                .padding(
                    top = innerPadding.calculateTopPadding(), // Respect TopAppBar
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
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
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp), // Increased spacing
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tambah Barang",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 26.sp, // Slightly larger for prominence
                            color = Color(0xFF1E88E5)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

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
                            unfocusedBorderColor = Color(0xFF757575),
                            errorBorderColor = Color(0xFFF44336)
                        ),
                        isError = namaBarang.isBlank() && status.isNotBlank() // Error if empty after interaction
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
                            unfocusedBorderColor = Color(0xFF757575),
                            errorBorderColor = Color(0xFFF44336)
                        ),
                        isError = kategoriBarang.isBlank() && status.isNotBlank()
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
                                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                                .menuAnchor(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1E88E5),
                                unfocusedBorderColor = Color(0xFF757575),
                                errorBorderColor = Color(0xFFF44336)
                            ),
                            isError = kondisiSelected == "Pilih Kondisi" && status.isNotBlank()
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
                            unfocusedBorderColor = Color(0xFF757575),
                            errorBorderColor = Color(0xFFF44336)
                        ),
                        isError = labtekId.isBlank() && status.isNotBlank()
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
                            unfocusedBorderColor = Color(0xFF757575),
                            errorBorderColor = Color(0xFFF44336)
                        ),
                        isError = pengelolaId.isBlank() && status.isNotBlank()
                    )

                    OutlinedTextField(
                        value = status,
                        onValueChange = { status = it },
                        label = { Text("Status") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF757575),
                            errorBorderColor = Color(0xFFF44336)
                        ),
                        isError = status.isBlank() && namaBarang.isNotBlank()
                    )

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
                            unfocusedBorderColor = Color(0xFF757575),
                            errorBorderColor = Color(0xFFF44336)
                        ),
                        isError = tanggalMasuk.isBlank() && status.isNotBlank()
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
                            modifier = Modifier.background(Color(0xFFF5F7FA)) // Consistent dialog background
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp)) // Increased spacing

                    Button(
                        onClick = {
                            if (namaBarang.isNotBlank() && kategoriBarang.isNotBlank() && kondisiSelected != "Pilih Kondisi" && labtekId.isNotBlank() && pengelolaId.isNotBlank() && status.isNotBlank() && tanggalMasuk.isNotBlank()) {
                                onSimpan(namaBarang, kategoriBarang, kondisiSelected, labtekId, pengelolaId, status, tanggalMasuk)
                                Toast.makeText(context, "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp), // Slightly taller button
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Simpan",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold // Bolder text
                        )
                    }
=======
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

        OutlinedTextField(
            value = kategoriBarang,
            onValueChange = { kategoriBarang = it },
            label = { Text("Kategori Barang") },
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
                    .menuAnchor()
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
>>>>>>> 5ec21fad4d76a3fc50c0c1c8f014bbf8ce8481a2
                }
            }
        }

        OutlinedTextField(
            value = labtekId,
            onValueChange = { labtekId = it },
            label = { Text("Labtek ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = pengelolaId,
            onValueChange = { pengelolaId = it },
            label = { Text("Pengelola ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tanggalMasuk,
            onValueChange = { /* Tidak diizinkan input manual */ },
            label = { Text("Tanggal Masuk") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Pilih Tanggal")
                }
            }
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
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (namaBarang.isNotBlank() && kategoriBarang.isNotBlank() && kondisiSelected != "Pilih Kondisi" && labtekId.isNotBlank() && pengelolaId.isNotBlank() && status.isNotBlank() && tanggalMasuk.isNotBlank()) {
                    onSimpan(namaBarang, kategoriBarang, kondisiSelected, labtekId, pengelolaId, status, tanggalMasuk)
                    Toast.makeText(context, "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50), // Warna hijau
                contentColor = Color.White // Warna teks putih
            )
        ) {
            Text("Simpan")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TambahBarangScreenPreview() {
    TambahBarangScreen(
        onSimpan = { _, _, _, _, _, _, _ -> }
    )
}

