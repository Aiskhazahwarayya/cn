package com.example.cn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cn.view.FormPendaftaran
import com.example.cn.view.TampilData
import com.example.cn.view.WelcomeScreen

enum class Halaman {
    Welcome,
    TampilData,
    FormPendaftaran
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val defaultNama = "Aiskha Zahwa Rayya"
    val defaultJK = "Perempuan"
    val defaultStatus = "Lajang"
    val defaultAlamat = "Bekasi"

    NavHost(
        navController = navController,
        startDestination = Halaman.Welcome.name,
        modifier = modifier
    ) {
        composable(route = Halaman.Welcome.name) {
            WelcomeScreen(
                onSubmitClick = {
                    navController.navigate(
                        "${Halaman.TampilData.name}/$defaultNama/$defaultJK/$defaultStatus/$defaultAlamat"
                    )
                }
            )
        }

        composable(route = Halaman.FormPendaftaran.name) {
            FormPendaftaran(
                onSubmitClick = { nama, jk, status, alamat ->
                    navController.navigate(
                        "${Halaman.TampilData.name}/$nama/$jk/$status/$alamat"
                    ) {
                        popUpTo(Halaman.FormPendaftaran.name) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "${Halaman.TampilData.name}/{nama}/{jk}/{status}/{alamat}",
            arguments = listOf(
                navArgument("nama") { defaultValue = defaultNama },
                navArgument("jk") { defaultValue = defaultJK },
                navArgument("status") { defaultValue = defaultStatus },
                navArgument("alamat") { defaultValue = defaultAlamat }
            )
        ) { backStackEntry ->
            val nama = backStackEntry.arguments?.getString("nama") ?: defaultNama
            val jk = backStackEntry.arguments?.getString("jk") ?: defaultJK
            val status = backStackEntry.arguments?.getString("status") ?: defaultStatus
            val alamat = backStackEntry.arguments?.getString("alamat") ?: defaultAlamat

            TampilData(
                namaLengkap = nama,
                jenisKelamin = jk,
                statusPerkawinan = status,
                alamat = alamat,
                onBerandaClick = {
                    // Navigasi ke Welcome dan bersihkan back stack
                    navController.navigate(Halaman.Welcome.name) {
                        popUpTo(Halaman.Welcome.name) { inclusive = true }
                    }
                },
                onFormulirClick = {
                    navController.navigate(Halaman.FormPendaftaran.name)
                }
            )
        }
    }
}