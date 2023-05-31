package com.example.traintrack.Actividad

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityView(activityViewModel: ActivityViewModel, navController: NavController) {
    val stepsState = remember { mutableStateOf(0) }
    val stepsToAddState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pasos realizados: ${stepsState.value}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LaunchedEffect(Unit) {
            activityViewModel.getSteps { steps ->
                stepsState.value = steps
            }
        }

        TextField(
            value = stepsToAddState.value,
            onValueChange = { newValue ->
                stepsToAddState.value = newValue
            },
            label = { Text(text = "Añadir pasos") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("homepage")

            }
        ){
            Text(text = "Atrás")
        }

        Button(
            onClick = {
                val stepsToAdd = stepsToAddState.value.toIntOrNull()
                if (stepsToAdd != null) {
                    activityViewModel.saveStepsToFirebase(stepsToAdd)
                } else {
                    Log.d("E", "Error guardr valores")
                }
            }
        ) {
            Text(text = "Guardar pasos")
        }
        Canvas(
            modifier = Modifier.size(200.dp)
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            drawCircle(
                color = Color.Blue,
                center = center,
                radius = radius,
                style = Stroke(width = 4.dp.toPx())
            )
        }


    }
}