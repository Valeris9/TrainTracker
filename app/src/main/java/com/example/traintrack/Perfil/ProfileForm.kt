package com.example.traintrack.Perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm (viewModel: PerfilViewModel, navController :NavController) {
    // Campos del formulario
    var nombre by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }

    Column {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = sexo,
            onValueChange = { sexo = it },
            label = { Text("Sexo") }
        )

        Button(
            onClick = {
                // Actualizar las propiedades del ViewModel con los valores del formulario
                viewModel.nombre = nombre
                viewModel.altura = altura.toFloatOrNull()!!
                viewModel.edad = edad.toIntOrNull()!!
                viewModel.peso = peso.toFloatOrNull()!!
                viewModel.sexo = sexo

                // Guardar el perfil en la base de datos
                viewModel.guardarPerfil()

                //Actualizar vista
                navController.navigate("perfil")
            }
        ) {
            Text("Guardar")
        }
    }
}