package com.example.traintrack.Weather

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.Weather.WeatherViewModel

@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
fun WeatherScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel,
    locationKey: String?
) {
    // Estado mutable para almacenar los datos del clima
    var weatherData by remember { mutableStateOf("") }


    // Efecto lanzado cuando cambia la clave de ubicación
    LaunchedEffect(locationKey) {
        if (locationKey != null) {
            // Obtener el clima actual mediante el ViewModel
            weatherViewModel.getCurrentWeather(locationKey) { data ->
                // Asignar los datos del clima al estado mutable
                weatherData = data
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar los datos del clima en un Texto
        Text(text = weatherData)

        Button(
            onClick = {
                navController.navigate("selectcity")

            }
        ){
            Text(text = "Seleccionar otra ciudad")
        }

        Button(
            onClick = { navController.navigate("perfil") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "Ir al perfil", color = Color.White)
        }

    }




}