import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mouse
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.hadiyarajesh.composetemplate.BarangLab

// Mapping nama ke icon opsional
fun getIconForItem(nama: String): ImageVector {
    return when {
        nama.contains("Monitor", ignoreCase = true) -> Icons.Default.Computer
        nama.contains("Keyboard", ignoreCase = true) -> Icons.Default.Keyboard
        nama.contains("Mouse", ignoreCase = true) -> Icons.Default.Mouse
        else -> Icons.Default.Computer
    }
}

@Composable
fun CekBarangLabScreen(
    daftarBarang: List<BarangLab>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cek Barang Lab Komputer",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(daftarBarang) { barang ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = getIconForItem(barang.nama),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = barang.nama,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Kondisi: ${barang.kondisi}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Lokasi: ${barang.lokasi}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
