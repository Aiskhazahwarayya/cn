package com.example.cn.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onSubmitClick: () -> Unit
) {

    val colorBackground = Color(0xFFE6E6FA)
    val colorPurple700 = Color(0xFF673AB7)
    val colorDeepPurple = Color(0xFF512DA8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 40.dp)
        ) {
            Text(
                text = "Selamat Datang",
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                color = colorPurple700,
                textAlign = TextAlign.Center
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CARD-LST",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = colorDeepPurple,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🪐",
                fontSize = 80.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "MOBILE APP",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorPurple700,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "2025",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorDeepPurple
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 60.dp)
        ) {
            Text(
                text = "Aiskha Zahwa Rayya",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = colorPurple700
            )

            Text(
                text = "20230140146",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = colorPurple700,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = onSubmitClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    // Menggunakan val colorDeepPurple
                    containerColor = colorDeepPurple
                )
            ) {
                Text(
                    text = "Submit",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}