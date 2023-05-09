package com.example.traintrack.Actividad

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ActivityView(caloriesBurned: Int, heartRate: Int, averagePace: String, distance: Float) {
    Row(
        modifier = Modifier.fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 60.dp)

        ) {
        Column(
            modifier = Modifier.weight(4f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calorías quemadas",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = caloriesBurned.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Frecuencia cardíaca",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = heartRate.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column(
            modifier = Modifier.weight(4f),
        ) {
            Text(
                text = "Ritmo medio",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = averagePace,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Kilómetros recorridos",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = distance.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
            .aspectRatio(1.1f)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                color = Color.LightGray,
                size = size,
                style = Stroke(width = 2.dp.toPx())
            )

            // Dibujamos un cuadrado rojo en el centro
            drawRect(
                color = Color.Red,
                topLeft = Offset(size.width / 2 - 25.dp.toPx(), size.height / 2 - 25.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(50.dp.toPx(), 50.dp.toPx())
            )
        }
    }
}
