package com.example.cn.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilData(
    namaLengkap: String,
    jenisKelamin: String,
    statusPerkawinan: String,
    alamat: String,
    tanggalLahir: String,  // <-- parameter tambahan
    onBerandaClick: () -> Unit,
    onFormulirClick: () -> Unit
) {

    val Purple500 = Color(0xFFB388FF)
    val Lavender = Color(0xFFF3E5F5)
    val LightPurple = Color(0xFFFFFFFF)
    val DeepPurple = Color(0xFF7C3AED)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "List Daftar Peserta",
                        color = Color.White,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple500
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Lavender)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // NAMA LENGKAP
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("NAMA LENGKAP", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                        Text(
                            text = namaLengkap,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Cursive,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // JENIS KELAMIN
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("JENIS KELAMIN", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                        Text(
                            text = jenisKelamin,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Cursive,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // STATUS PERKAWINAN
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("STATUS PERKAWINAN", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                        Text(
                            text = statusPerkawinan,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Cursive,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // ALAMAT
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ALAMAT", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                        Text(
                            text = alamat,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Cursive,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // TANGGAL LAHIR
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("TANGGAL LAHIR", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                        Text(
                            text = tanggalLahir,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Cursive,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            // Tombol Beranda dan Formulir
            Column {
                Button(
                    onClick = onBerandaClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DeepPurple),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Beranda", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onFormulirClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightPurple.copy(alpha = 0.6f)),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Formulir Pendaftaran", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = DeepPurple)
                }
            }
        }
    }
}
