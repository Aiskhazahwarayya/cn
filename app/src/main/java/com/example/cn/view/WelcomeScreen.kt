package com.example.cn.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cn.R


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
            modifier = Modifier.padding(top = 60.dp)
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Selamat Datang",
                fontSize = 37.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace,
                color = colorPurple700,
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoumy), // pastikan file ada di res/drawable
                contentDescription = "Logo",
                modifier = Modifier.size(300.dp)
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
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorDeepPurple
                )
            ) {
                Text(
                    text = "Submit",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}