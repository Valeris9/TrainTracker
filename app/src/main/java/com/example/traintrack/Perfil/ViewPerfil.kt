package com.example.traintrack.Perfil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileView(name: String, age: Int, exercises: Int,height: Float ,weight: Float, registrationDate: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nombre: $name")
        Text(text = "Edad: $age")
        Text(text = "Ejercicios realizados: $exercises")
        Text(text = "Altura: $height m" )
        Text(text = "Peso: $weight kg")
        Text(text = "Fecha de registro: $registrationDate")

        Button(
            onClick = {}, //TODO
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "Historial de ejercicios", color = Color.White)
        }
    }


}