package com.hadiyarajesh.composetemplate.ui.barang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun BarangTable(barangList: List<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                }
                Divider()
            }
        }
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
