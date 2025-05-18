package com.hadiyarajesh.composetemplate.ui.barang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.hadiyarajesh.composetemplate.ui.barang.BarangViewModel
import com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CekBarangLabTableScreen(
    barangViewModel: BarangViewModel = viewModel(),
    onEditClick: (BarangLab) -> Unit = {},
    onDeleteClick: (BarangLab) -> Unit = {}
) {
    val uiState by barangViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        barangViewModel.loadBarang()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Barang Lab Komputer",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Tabel dalam Card
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Header Tabel
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeaderCell("No", 1f)
                    HeaderCell("Nama Barang", 3f)
                    HeaderCell("Kondisi", 2f)
                    HeaderCell("Lokasi", 2f)
                    HeaderCell("Aksi", 2f)
                }

                // Isi Tabel
                LazyColumn {
                    itemsIndexed(uiState.daftarBarang) { index, barang ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DataCell((index + 1).toString(), 1f)
                            DataCell(barang.nama, 3f)

                            Box(
                                modifier = Modifier.weight(2f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = barang.kondisi,
                                    color = when (barang.kondisi.lowercase()) {
                                        "baik" -> Color(0xFF2E7D32)
                                        "rusak" -> Color(0xFFC62828)
                                        else -> MaterialTheme.colorScheme.onSurface
                                    },
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            DataCell(barang.lokasi, 2f)

                            Row(
                                modifier = Modifier.weight(2f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = { onEditClick(barang) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                IconButton(
                                    onClick = { onDeleteClick(barang) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = MaterialTheme.colorScheme.error
                                    )
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
fun HeaderCell(text: String, weight: Float) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DataCell(text: String, weight: Float) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
