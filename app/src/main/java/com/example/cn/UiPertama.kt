package com.example.cn.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormulirPendaftaran(modifier: Modifier = Modifier) {
    // State untuk input form
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    var textStatus by remember { mutableStateOf("") }

    // State BARU untuk menyimpan data yang akan ditampilkan di dialog
    var nama by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    // State untuk dialog
    var showDialog by remember { mutableStateOf(false) }

    // List options
    val genderList = listOf("Laki-laki", "Perempuan")
    val statusList = listOf("Janda", "Lajang", "Duda")

    // Warna tema
    val backgroundLavender = Color(0xFFF3E5F5)
    val purpleButton = Color(0xFF7C3AED)
    val borderColor = Color.LightGray // Warna untuk border

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundLavender)
            .padding(horizontal = 16.dp)
    ) {
        // Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFD1B3FF), Color(0xFFB388FF))
                    )
                )
                .padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Formulir Pendaftaran",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 16.dp) // Sesuaikan padding agar tidak terlalu ke bawah
            )
        }

        // Kolom formulir utama (bagian putih)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp) // Padding atas untuk memisahkan dari header
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(1.dp, borderColor), // Ketebalan 1dp, warna LightGray
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp) // Padding internal agar konten tidak menempel pada border
        ) {
            // Nama Lengkap
            Text(
                text = "NAMA LENGKAP",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = textNama,
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "Isian nama lengkap", color = Color.Gray) },
                onValueChange = {
                    textNama = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            // Jenis Kelamin
            Text(
                text = "JENIS KELAMIN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
            Column {
                genderList.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = textJK == item,
                                onClick = { textJK = item }
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = textJK == item,
                            onClick = {
                                textJK = item
                            }
                        )
                        Text(text = item, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            // Status Perkawinan
            Text(
                text = "STATUS PERKAWINAN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
            Column {
                statusList.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = textStatus == item,
                                onClick = { textStatus = item }
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = textStatus == item,
                            onClick = {
                                textStatus = item
                            }
                        )
                        Text(text = item, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            // Alamat
            Text(
                text = "ALAMAT",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
            OutlinedTextField(
                value = textAlamat,
                singleLine = true,
                placeholder = { Text(text = "Alamat", color = Color.Gray) },
                shape = RoundedCornerShape(8.dp),
                onValueChange = {
                    textAlamat = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
        }

        // Button Submit
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(50.dp),
            enabled = textNama.isNotEmpty() && textJK.isNotEmpty() &&
                    textStatus.isNotEmpty() && textAlamat.isNotEmpty(),
            onClick = {
                nama = textNama
                jenis = textJK
                status = textStatus
                alamat = textAlamat
                showDialog = true
            },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = purpleButton,
                disabledContainerColor = purpleButton.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = "Submit",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Pop-up Dialog untuk menampilkan hasil
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "Data Pendaftaran",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },

                text = {
                    Column {
                        Text(text = "Nama : $nama", fontSize = 14.sp)
                        Text(text = "Gender : $jenis", fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Status : $status", fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Alamat : $alamat", fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("OK", color = purpleButton, fontWeight = FontWeight.Bold)
                    }
                },
                containerColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}