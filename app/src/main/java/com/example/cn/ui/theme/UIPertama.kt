package com.example.cn.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
    // State untuk input form (struktur sama seperti praktikum4userinput)
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    var textStatus by remember { mutableStateOf("") }

    // State untuk dialog
    var showDialog by remember { mutableStateOf(false) }

    // List options
    val genderList = listOf("Laki-laki", "Perempuan")
    val statusList = listOf("Janda", "Lajang", "Duda")

    // Warna tema
    val backgroundLavender = Color(0xFFF3E5F5)
    val purpleButton = Color(0xFF7C3AED)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundLavender)
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
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Formulir Pendaftaran",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 20.dp, top = 16.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card Form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Input Nama
                    Text(
                        text = "NAMA LENGKAP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    OutlinedTextField(
                        value = textNama,
                        onValueChange = { textNama = it },
                        placeholder = { Text("Isikan nama lengkap") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Jenis Kelamin
                    Text(
                        text = "JENIS KELAMIN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    genderList.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (textJK == item),
                                    onClick = { textJK = item }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (textJK == item),
                                onClick = { textJK = item }
                            )
                            Text(text = item)
                        }
                    }

                    // Status Perkawinan
                    Text(
                        text = "STATUS PERKAWINAN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    statusList.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (textStatus == item),
                                    onClick = { textStatus = item }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (textStatus == item),
                                onClick = { textStatus = item }
                            )
                            Text(text = item)
                        }
                    }

                    // Input Alamat
                    Text(
                        text = "ALAMAT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    OutlinedTextField(
                        value = textAlamat,
                        onValueChange = { textAlamat = it },
                        placeholder = { Text("Alamat lengkap") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Button Submit
                    Button(
                        onClick = { showDialog = true },
                        enabled = textNama.isNotEmpty() &&
                                textJK.isNotEmpty() &&
                                textStatus.isNotEmpty() &&
                                textAlamat.isNotEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = purpleButton)
                    ) {
                        Text(
                            text = "Submit",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Alert Dialog
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Tutup", color = purpleButton)
                        }
                    },
                    title = { Text("Data Pendaftaran") },
                    text = {
                        Column {
                            Text("Nama: $textNama")
                            Text("Jenis Kelamin: $textJK")
                            Text("Status: $textStatus")
                            Text("Alamat: $textAlamat")
                        }
                    }
                )
            }
        }
    }
}