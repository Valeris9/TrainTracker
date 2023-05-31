package com.example.traintrack

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Weather.WeatherViewModel
import com.example.traintrack.Actividad.ActivityView
import com.example.traintrack.Actividad.ActivityViewModel
import com.example.traintrack.Actividad.MetricsView
import com.example.traintrack.PaginaPrincipal.HomePage
import com.example.traintrack.PaginaPrincipal.MapScreen
import com.example.traintrack.PaginaPrincipal.MapViewModel
import com.example.traintrack.Perfil.PerfilViewModel
import com.example.traintrack.Perfil.ProfileView
import com.example.traintrack.Weather.SelectCityView
import com.example.traintrack.Weather.WeatherScreen
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
        // Configuración de la interfaz de usuario
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

    fun requestActivityRecognitionPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && PackageManager.PERMISSION_GRANTED !=
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                REQUEST_CODE_ACTIVITY_RECOGNITION
            )
        }
    }
}
@Composable
fun TrainTracker() {
    val navController = rememberNavController()
    val weatherViewModel = WeatherViewModel(context = LocalContext.current)

    // Configuración del sistema de navegación. Selecciona login como pagina inicial
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, LoginScreenViewModel()) }
        composable("homepage") { HomePage(navController) }
        composable("map") { MapScreen(mapViewModel = MapViewModel()) }
        composable("perfil") {
            val perfilViewModel = remember { PerfilViewModel() }
            ProfileView(
                perfilViewModel = perfilViewModel,
                navController = navController,
            )
        }
        composable("weather/{locationKey}") { backStackEntry ->
            val locationKey = backStackEntry.arguments?.getString("locationKey")
            WeatherScreen(navController, weatherViewModel, locationKey)
        }

        composable("selectcity"){SelectCityView(navController, weatherViewModel = weatherViewModel)}

        composable("fitness") {
            val activity = LocalContext.current as Activity
            val fitnessApi = FitnessApi(activity)
            val activityViewModel = remember { ActivityViewModel(fitnessApi, activity) }
            ActivityView(activityViewModel, navController)

            LaunchedEffect(Unit) {
                (activity as MainActivity).requestActivityRecognitionPermission()
                activityViewModel.getSteps { steps ->
                    activityViewModel.saveStepsToFirebase(steps)
                }
            }
        }
        composable("calcular"){ MetricsView(caloriesBurned = 1000, distance = 120.0, steps = 12000 )}

    }
}

