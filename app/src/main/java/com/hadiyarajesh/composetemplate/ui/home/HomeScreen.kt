package com.hadiyarajesh.composetemplate.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hadiyarajesh.composetemplate.R
import com.hadiyarajesh.composetemplate.data.entity.Image
import com.hadiyarajesh.composetemplate.ui.components.ErrorItem
import com.hadiyarajesh.composetemplate.ui.components.LoadingIndicator
import com.hadiyarajesh.composetemplate.ui.components.VerticalSpacer
import com.hadiyarajesh.composetemplate.utility.Constants
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource


@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
        LocalContext.current
    val homeScreenUiState by remember { viewModel.uiState }.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = homeScreenUiState,
        loadData = { viewModel.loadData() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeScreenUiState,
    loadData: () -> Unit
) {
    LaunchedEffect(Unit) {
        loadData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "HOME") }
            )
        }


    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                is HomeScreenUiState.Initial -> {}

                is HomeScreenUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is HomeScreenUiState.Success -> {
                    HomeScreenContent(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is HomeScreenUiState.Error -> {
                    ErrorItem(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        text = uiState.msg
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier
) {


    RoundedCornerShape(16.dp)
    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.erh),
            contentDescription = "Deskripsi aksesibilitas",
            modifier = Modifier.size(200.dp)
        )


        Text(
            text = "Selamat Datang di Aplikasi ERH",
            style = MaterialTheme.typography.titleSmall
        )
        VerticalSpacer(size = 20)
        VerticalSpacer(size = 180)

        // Row untuk button Cek Barang dan Tambah Barang
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Button Cek Barang
            Button(
                onClick = {
                    Toast.makeText(context, "Membuka menu Cek Barang", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Cek Barang")
            }

            // Button Tambah Barang
            Button(
                onClick = {
                    Toast.makeText(context, "Membuka menu Tambah Barang", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text("Tambah Barang")
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            uiState = HomeScreenUiState.Success(
                data = Image(
                    description = stringResource(id = R.string.welcome_message),
                    altText = stringResource(id = R.string.image),
                    url = Constants.IMAGE_URL
                )
            ),
            loadData = {}
        )
    }
}