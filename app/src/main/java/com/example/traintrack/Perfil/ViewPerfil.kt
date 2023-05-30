package com.example.traintrack.Perfil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.reflect.KProperty

@Composable
fun ProfileView(
    perfilViewModel: PerfilViewModel,
    navController: NavController
) {
    // Recopilar el estado del perfilData del PerfilViewModel
    val perfilData by perfilViewModel.perfilData.collectAsState()

    // Obtener el perfil al cargar la vista
    perfilViewModel.obtenerPerfil()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar los datos del perfil si están disponibles
        if (perfilData != null) {
            Text(text = "Nombre: ${perfilData!!["nombre"]}")
            Text(text = "Edad: ${perfilData!!["edad"]}")
            Text(text = "Altura: ${perfilData!!["altura"]} m")
            Text(text = "Peso: ${perfilData!!["peso"]} kg")
        }


        Spacer(modifier = Modifier.height(50.dp))


        // Formulario para editar el perfil
        Text(text = "Modificar Perfil: ")
        ProfileForm(viewModel = perfilViewModel, navController)

        Button(
            onClick = { navController.navigate("homepage") },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "Volver atrás", color = Color.White)
        }
    }
}

private operator fun Float.getValue(nothing: Nothing?, property: KProperty<*>): Any {
    return this
}

private operator fun Int.getValue(nothing: Nothing?, property: KProperty<*>): Any {
    return this
}

private operator fun String.getValue(nothing: Nothing?, property: KProperty<*>): Any {
    return this
}

