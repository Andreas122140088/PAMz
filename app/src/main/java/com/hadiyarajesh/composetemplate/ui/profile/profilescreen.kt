import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.composetemplate.ui.profile.ProfileData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(profile: ProfileData) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder untuk foto profil
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                // Tambahkan komponen Image di sini jika tersedia
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nama
            Text(
                text = profile.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Detail Profil
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                ProfileItem(label = "Email", value = profile.email)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileItem(label = "Status", value = profile.status)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileItem(label = "Peran", value = profile.role)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileItem(label = "Tanggal Masuk", value = profile.joinDate)
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val sampleProfile = ProfileData(
        name = "John Doe",
        email = "john.doe@example.com",
        status = "Aktif",
        role = "Administrator",
        joinDate = "18 Mei 2025"
    )
    ProfileScreen(profile = sampleProfile)
}
