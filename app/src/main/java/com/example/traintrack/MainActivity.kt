package com.example.traintrack

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.traintrack.Inicio.LogoAndButtonsScreen
import com.example.traintrack.Perfil.ProfileView
import com.example.traintrack.Registro.RegisterScreen
import com.example.traintrack.ui.theme.TrainTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainTrackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileView("Victor",21,10,76.3f,"20/05/2023")
                }
            }
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrainTrackTheme {
        Greeting("Android")
    }
}*/