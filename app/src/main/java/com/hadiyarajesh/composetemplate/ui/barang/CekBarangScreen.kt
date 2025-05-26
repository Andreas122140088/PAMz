package com.hadiyarajesh.composetemplate.ui.barang

import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp


@Composable
fun BarangTable(barangList: List<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab>) {
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
                                Text(barang.nama, style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF0C387D), fontWeight = FontWeight.Bold))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Kategori: ${barang.kategori}", color = Color(0xFF1976D2))
                                Text("Kondisi: ${barang.kondisi}", color = Color(0xFF1976D2))
                                Text("Status: ${barang.status}", color = Color(0xFF42A5F5))
                                Text("Tanggal Masuk: ${barang.tanggalMasuk}", color = Color(0xFF1565C0))
                            }
                        }
                    }
                }
            }
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
