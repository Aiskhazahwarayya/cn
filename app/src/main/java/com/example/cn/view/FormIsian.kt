package com.example.cn.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPendaftaran(
    onSubmitClick: (nama: String, jk: String, status: String, alamat: String, tglLahir: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    var textStatus by remember { mutableStateOf("") }
    var textTanggal by remember { mutableStateOf("") }

    var showSubmitDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val genderList = listOf("Laki-laki", "Perempuan")

    val listStatus = listOf("Lajang", "Janda", "Duda")
    var expanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("Belum dipilih") }

    val backgroundLavender = Color(0xFFF3E5F5)
    val purpleButton = Color(0xFF7C3AED)
    val headerGradientStart = Color(0xFFD1B3FF)
    val headerGradientEnd = Color(0xFFB388FF)

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = purpleButton,
            secondary = purpleButton,
            tertiary = purpleButton
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundLavender)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(headerGradientStart, headerGradientEnd)
                        )
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Formulir Pendaftaran",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
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
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        // PENYESUAIAN RUANG: Mengurangi jarak vertikal
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Nama
                        Text("NAMA LENGKAP", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray) // FONT DIKECILKAN
                        OutlinedTextField(
                            value = textNama,
                            onValueChange = { textNama = it },
                            placeholder = { Text("Isikan nama lengkap") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        // Jenis Kelamin (KEMBALI KE HORIZONTAL)
                        Text("JENIS KELAMIN", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray) // FONT DIKECILKAN
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            genderList.forEach { item ->
                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .selectable(selected = (textJK == item), onClick = { textJK = item })
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(selected = (textJK == item), onClick = { textJK = item })
                                    Text(text = item, fontSize = 15.sp)
                                }
                            }
                        }

                        // Status Perkawinan (KEMBALI KE HORIZONTAL)
                        Text("Status", fontSize = 17.sp, color = Color.Black)
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            OutlinedTextField(
                                value = selectedStatus,
                                onValueChange = {},
                                label = { Text("Status Perkawinan") },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                listStatus.forEach { status ->
                                    DropdownMenuItem(
                                        text = { Text(status) },
                                        onClick = {
                                            selectedStatus = status
                                            textStatus = status
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Alamat
                        Text("ALAMAT", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray) // FONT DIKECILKAN
                        OutlinedTextField(
                            value = textAlamat,
                            onValueChange = { textAlamat = it },
                            placeholder = { Text("Alamat lengkap") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 60.dp, max = 100.dp)
                        )

                        // Tanggal Lahir
                        Text("TANGGAL LAHIR", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray) // FONT DIKECILKAN
                        OutlinedTextField(
                            value = textTanggal,
                            onValueChange = { /* readOnly */ },
                            placeholder = { Text("Pilih tanggal lahir") },
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    Icons.Default.CalendarToday,
                                    contentDescription = "Pilih Tanggal Lahir",
                                    tint = purpleButton,
                                    modifier = Modifier.clickable { showDatePicker = true }
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showDatePicker = true },
                        )

                        Spacer(modifier = Modifier.height(5.dp)) // JARAK LEBIH KECIL

                        // Tombol Submit
                        Button(
                            onClick = { showSubmitDialog = true },
                            enabled = textNama.isNotEmpty() &&
                                    textJK.isNotEmpty() &&
                                    textStatus.isNotEmpty() &&
                                    textAlamat.isNotEmpty() &&
                                    textTanggal.isNotEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp), // TOMBOL SEDIKIT LEBIH PENDEK
                            shape = RoundedCornerShape(25.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = purpleButton)
                        ) {
                            Text(
                                text = "Submit Data",
                                color = Color.White,
                                fontSize = 16.sp, // FONT TOMBOL LEBIH KECIL
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                // Spacer(modifier = Modifier.height(16.dp)) // DIHAPUS/DIKURANGI JAUH

                // ... Date Picker Dialogs dan Submit Dialogs tetap sama

                // Date Picker Composable M3
                if (showDatePicker) {
                    val datePickerState = rememberDatePickerState(
                        initialSelectedDateMillis = System.currentTimeMillis()
                    )

                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        textTanggal = formatter.format(Date(millis))
                                    }
                                    showDatePicker = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("Tutup")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                // Dialog Konfirmasi Submit
                if (showSubmitDialog) {
                    AlertDialog(
                        onDismissRequest = { showSubmitDialog = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onSubmitClick(textNama, textJK, textStatus, textAlamat, textTanggal)
                                    showSubmitDialog = false
                                }
                            ) {
                                Text("OK", color = purpleButton, fontWeight = FontWeight.Bold)
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
}