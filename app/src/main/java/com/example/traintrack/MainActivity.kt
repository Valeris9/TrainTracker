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
import com.example.traintrack.Actividad.ActivityView
import com.example.traintrack.Historial.Activity
import com.example.traintrack.Historial.HistoryView
import com.example.traintrack.Inicio.StartScreen
import com.example.traintrack.PaginaPrincipal.HomePage
import com.example.traintrack.Perfil.ProfileView
import com.example.traintrack.Registro.RegisterScreen
import com.example.traintrack.ui.theme.TrainTrackTheme
import java.time.LocalDateTime

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
                    //ProfileView("Victor",21,10,76.3f,"20/05/2023")
                    //ActivityView(caloriesBurned = 200, heartRate = 65, averagePace ="5.3 km/h" , distance = 33.3f )
                    //RegisterScreen()
                    //LoginScreen()
                    //StartScreen()
                    //HomePage()

                    val activities = listOf(
                        Activity(1, "Actividad 1", LocalDateTime.now()),
                        Activity(2, "Actividad 2", LocalDateTime.now().minusDays(1)),
                        Activity(3, "Actividad 3", LocalDateTime.now().minusDays(2))
                    )

                    HistoryView(activities = activities)





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