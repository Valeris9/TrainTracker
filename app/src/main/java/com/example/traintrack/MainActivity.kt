package com.example.traintrack

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.traintrack.ui.theme.TrainTrackTheme
import com.example.traintrack.Registro.LoginScreen
import com.example.traintrack.Registro.LoginScreenViewModel
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.traintrack.PaginaPrincipal.HomePage
import com.example.traintrack.PaginaPrincipal.MapScreen
import com.example.traintrack.Perfil.PerfilViewModel
import com.example.traintrack.Perfil.ProfileView
import com.google.firebase.FirebaseApp

//import com.example.traintrack.Weather.WeatherScreen


class MainActivity : ComponentActivity() {

    companion object {
        private const val REQUEST_CODE_ACTIVITY_RECOGNITION = 1001
        private val LOCATION_PERMISSION_REQUEST_CODE = 1002

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
            setContent {
                TrainTrackTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TrainTracker()
                    }
                }
            }

    }
}
@Composable
fun TrainTracker() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, LoginScreenViewModel()) }
        composable("homepage") { HomePage(navController) }
        composable("map") { MapScreen() }
        composable("perfil"){ ProfileView(
            perfilViewModel = PerfilViewModel(),
            navController
        )}
        //composable("weather"){ WeatherScreen(navController)}
    }
}