package com.hadiyarajesh.composetemplate.ui.home

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hadiyarajesh.composetemplate.R
import com.hadiyarajesh.composetemplate.data.entity.Image
import com.hadiyarajesh.composetemplate.ui.components.ErrorItem
import com.hadiyarajesh.composetemplate.ui.components.LoadingIndicator
import com.hadiyarajesh.composetemplate.utility.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        loadData = { viewModel.loadData() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeScreenUiState,
    loadData: () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        loadData()
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(56.dp))
        // Latar belakang biru melengkung dengan lingkaran di tengah untuk logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    color = Color(0xF7276BB4),
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                )
        ) {

            // Lingkaran putih untuk membungkus logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomCenter)
                    .graphicsLayer { translationY = 60f } // setengah keluar dari box biru
                    .background(color = Color.White, shape = RoundedCornerShape(60.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.erh),
                    contentDescription = "Deskripsi aksesibilitas",
                    modifier = Modifier.size(90.dp)
                )
            }
        }

        Scaffold(
            topBar = {}, // Remove title from AppBar
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Spacer agar logo turun ke bawah HOME
                    Spacer(modifier = Modifier.height(160.dp)) // lebih besar agar tidak dobel logo
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
                                    .padding(40.dp)
                                    .fillMaxSize(),
                                text = uiState.msg
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top section: Image and Welcome Text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 40.dp) // Turunkan agar rapi di bawah box biru
        ) {
            Text(
                text = "Selamat Datang di Aplikasi ERH",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        // Bottom section: Logout Button
        Button(
            onClick = {
                Toast.makeText(context, "Logging out", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            )
        ) {
            Text("Log Out")
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
