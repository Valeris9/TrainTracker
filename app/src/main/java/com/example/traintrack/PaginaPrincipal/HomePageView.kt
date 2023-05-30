package com.example.traintrack.PaginaPrincipal

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    // Definición de la pantalla principal utilizando el componente Scaffold de Jetpack Compose
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TrainTrack") }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Botón para navegar a la pantalla de perfil
                Button(
                    onClick = {navController.navigate("perfil")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                ) {
                    Text(
                        "Perfil"
                        )
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Botón para mostrar el historial
                Button(
                    onClick = { navController.navigate("selectcity") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                ) {
                    Text(text = "Mostar Tiempo")
                }
                // Botón para iniciar una actividad
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    ) {
                    Text(text = "Iniciar Actividad")
                }
                Spacer(modifier = Modifier.height(160.dp))
                // Texto que permite abrir Google Maps al hacer clic
                Text(
                    text = "Abrir Google Maps",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("map")
                    })
                )
            }
        }
    )
}