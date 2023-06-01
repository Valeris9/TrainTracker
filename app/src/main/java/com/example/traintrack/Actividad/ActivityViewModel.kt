package com.example.traintrack.Actividad

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.traintrack.DatabaseReference
import com.example.traintrack.FitnessApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * La parte de Google Fitness no se ha podido testear bien, debido a que requería autorizar la App
 * con OAuth 2.0 por el uso de datos sensibles (Pasos, calorias quemadas...). Para poder demostrar
 * que se ha usado, dejamos adelante el código que se conecta a Google Fit correctamente y hace
 * las requests, sabemos que lo hace correctaemente debido a que los logs nos dan este error
 * de que falta autorizar por parte de Google la APP.
 */
class ActivityViewModel(private val fitnessApi: FitnessApi, private val activity: Activity) : ViewModel() {
    // Declaración de variables
    private lateinit var database: DatabaseReference
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: FirebaseUser? = firebaseAuth.currentUser
    private var _pasosData: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pasosData: StateFlow<Int?> = _pasosData

    // Método para obtener los pasos de la actividad
    fun getSteps(callback: (Int) -> Unit) {
        fitnessApi.readSteps(activity) { steps ->
            _pasosData.value = steps
            callback(steps)
        }
    }

    // Método para guardar los pasos en Firebase
    fun saveStepsToFirebase(steps: Int) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            val databaseReference = DatabaseReference()
            val stepsData: HashMap<String, Any> = hashMapOf(
                "pasos" to steps
            )
            databaseReference.guardarPasos(stepsData, userId)
        } else {
            Log.e(TAG, "Error al obtener el ID del usuario actual")
        }
    }

    // Método para recibir los pasos desde Firebase
    fun receiveStepsFromFirebase() {
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            val databaseReference = DatabaseReference()
            databaseReference.recibirPasos(userId) { steps ->
                _pasosData.value = steps
            }
        } else {
            Log.e(TAG, "Error al obtener el ID del usuario actual")
        }
    }

    fun getCaloriesBurned(weight: Float, callback: (Float) -> Unit) {
        val steps = _pasosData.value ?: 0
        val caloriesBurned = fitnessApi.calculateCaloriesBurned(steps, weight)
        callback(caloriesBurned)
    }


}