package com.example.traintrack.Actividad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MetricsView(caloriesBurned: Int, distance: Double, steps: Int) {
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

        Text(
            text = "Calorías quemadas: $caloriesBurned",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Distancia recorrida: $distance km",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Pasos realizados: $steps",
            style = MaterialTheme.typography.bodySmall
        )
    }
}