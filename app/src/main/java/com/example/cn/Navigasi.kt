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
import java.net.URLEncoder // Tambahkan import ini
import java.net.URLDecoder // Tambahkan import ini
import java.nio.charset.StandardCharsets // Tambahkan import ini

enum class Halaman {
    Welcome,
    TampilData, // Rute default (selalu Aiskha)
    FormPendaftaran,
    TampilDataArg // Rute khusus untuk data dari formulir
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Halaman.Welcome.name,
        modifier = modifier
    ) {
        // Halaman 1: Welcome
        composable(route = Halaman.Welcome.name) {
            WelcomeScreen(
                onSubmitClick = {
                    // Dari Welcome -> ke TampilData (Rute Default Aiskha)
                    navController.navigate(Halaman.TampilData.name)
                }
            )
        }

        // Halaman 2: Tampil Data (Rute Default - Selalu Aiskha)
        composable(route = Halaman.TampilData.name) {
            TampilData(
                namaLengkap = "Aiskha",
                jenisKelamin = "Perempuan",
                statusPerkawinan = "Lajang",
                alamat = "Bekasi",
                tanggalLahir = "13-10-2004",
                onBerandaClick = {
                    navController.navigate(Halaman.Welcome.name) {
                        popUpTo(Halaman.Welcome.name) { inclusive = true }
                    }
                },
                onFormulirClick = {
                    navController.navigate(Halaman.FormPendaftaran.name)
                }
            )
        }

        // Halaman 3: Form Pendaftaran
        composable(route = Halaman.FormPendaftaran.name) {
            FormPendaftaran(
                onSubmitClick = { nama, jk, status, alamat, tglLahir ->
                    // PERBAIKAN KRUSIAL: Lakukan URL ENCODING sebelum navigasi
                    val charset = StandardCharsets.UTF_8.toString()
                    val encodedNama = URLEncoder.encode(nama, charset)
                    val encodedJk = URLEncoder.encode(jk, charset)
                    val encodedStatus = URLEncoder.encode(status, charset)
                    val encodedAlamat = URLEncoder.encode(alamat, charset)
                    val encodedTglLahir = URLEncoder.encode(tglLahir, charset)

                    // Navigasi ke rute TampilDataArg dengan data yang sudah di-encode
                    navController.navigate(
                        "${Halaman.TampilDataArg.name}/$encodedNama/$encodedJk/$encodedStatus/$encodedAlamat/$encodedTglLahir"
                    ) {
                        // MENGHAPUS FormPendaftaran dari stack agar tombol back kembali ke Welcome
                        popUpTo(Halaman.FormPendaftaran.name) { inclusive = true }
                    }
                }
            )
        }


        // Halaman 4: Tampil Data ARGUMEN (Rute Khusus untuk Form Submit)
        composable(
            route = "${Halaman.TampilDataArg.name}/{nama}/{jk}/{status}/{alamat}/{tglLahir}",
            arguments = listOf(
                navArgument("nama") { defaultValue = "-" },
                navArgument("jk") { defaultValue = "-" },
                navArgument("status") { defaultValue = "-" },
                navArgument("alamat") { defaultValue = "-" },
                navArgument("tglLahir") { defaultValue = "-" }
            )
        ) { backStackEntry ->

            // PERBAIKAN KRUSIAL: Lakukan URL DECODING saat menerima data
            val charset = StandardCharsets.UTF_8.toString()
            val nama = URLDecoder.decode(backStackEntry.arguments?.getString("nama") ?: "-", charset)
            val jk = URLDecoder.decode(backStackEntry.arguments?.getString("jk") ?: "-", charset)
            val status = URLDecoder.decode(backStackEntry.arguments?.getString("status") ?: "-", charset)
            val alamat = URLDecoder.decode(backStackEntry.arguments?.getString("alamat") ?: "-", charset)
            val tglLahir = URLDecoder.decode(backStackEntry.arguments?.getString("tglLahir") ?: "-", charset)


            TampilData(
                namaLengkap = nama,
                jenisKelamin = jk,
                statusPerkawinan = status,
                alamat = alamat,
                tanggalLahir = tglLahir,
                onBerandaClick = {
                    // Kembali ke Welcome
                    navController.navigate(Halaman.Welcome.name) {
                        popUpTo(Halaman.Welcome.name) { inclusive = true }
                    }
                },
                onFormulirClick = {
                    // Navigasi ke Form Pendaftaran
                    navController.navigate(Halaman.FormPendaftaran.name)
                }
            )
        }
    }
}