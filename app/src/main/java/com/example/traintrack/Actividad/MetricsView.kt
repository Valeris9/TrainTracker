package com.example.traintrack.Actividad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MetricsView( distance: Double, steps: Int, navController : NavController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Esta pantalla debería de calcular al momento las calorías quemadas en base a los pasos realizados y mostrar las distintas métricas del usuario, pero al no poder autorizar la app con OAuth 2.0, no se puede implementar",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 24.dp)
                    .background(Color.Yellow)
            )
        }

        val caloriesBurned: MutableState<Int> = remember { mutableStateOf(0) }

        /**
             Aquí, en lugar de hardcodear las calorias se podrian recibir así si pudiesemos usar la
             API correctamente:

        * val activityViewModel = viewModel<ActivityViewModel>()
        * val weight = 70f // Peso del usuario (ejemplo)
        * LaunchedEffect(Unit) {
        *    activityViewModel.getCaloriesBurned(weight) { calories ->
        *        // Actualiza el valor de las calorías quemadas en el estado
        *        caloriesBurned.value = calories
        *    }
        * }
        */

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Calorías quemadas: $caloriesBurned",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Distancia recorrida: $distance km",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Pasos realizados: $steps",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    Button(
        onClick = {
            navController.navigate("homepage")

        }
    ){
        Text(text = "Atrás")
    }

}