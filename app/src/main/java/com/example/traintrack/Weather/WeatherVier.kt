package com.example.traintrack.Weather
/*
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Weather.WeatherViewModel
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
fun WeatherScreen(navController: NavController) {
    var weatherData by remember { mutableStateOf("") }
    val weatherViewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory(context))

    LaunchedEffect(Unit) {
        try {
            // Obtener la ubicación actual del dispositivo
            val latitude = 1.1 // Obtener la latitud actual
            val longitude = 1.3 // Obtener la longitud actual

            weatherViewModel.getCurrentWeather(latitude, longitude) { data ->
                weatherData = data
            }
        } catch (e: Exception) {
            // Manejar cualquier excepción que ocurra aquí, como mostrar un mensaje de error al usuario.
        }
    }

    Column {
        Text(text = "Weather Information")
        Text(text = weatherData)
    }
}*/