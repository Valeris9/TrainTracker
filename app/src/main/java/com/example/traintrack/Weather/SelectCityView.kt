package com.example.traintrack.Weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.Weather.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCityView(navController: NavController, weatherViewModel: WeatherViewModel) {
    // Estado mutable para almacenar el valor del campo de texto de la ciudad
    val cityInput = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto de instrucción para seleccionar la ciudad
        Text(text = "Selecciona la ciudad para ver el tiempo")

        // Campo de texto para ingresar la ciudad
        TextField(
            value = cityInput.value,
            onValueChange = { cityInput.value = it },
            label = { Text("Ciudad") }
        )

        // Botón para obtener la clave de ubicación
        Button(
            onClick = {
                val city = cityInput.value.text
                // Llamar al método searchCityByName del weatherViewModel para buscar la clave de ubicación
                weatherViewModel.searchCityByName(city) {locationKey ->
                    if (locationKey != null){
                        // Navegar a la pantalla de clima con la clave de ubicación como argumento
                        navController.navigate("weather/${locationKey}")

                    }
                }
            }
        ) {
            Text(text = "Buscar")
        }
    }
}