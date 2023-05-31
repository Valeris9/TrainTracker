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

class ActivityViewModel(private val fitnessApi: FitnessApi, private val activity: Activity) : ViewModel() {

    private lateinit var database: DatabaseReference
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: FirebaseUser? = firebaseAuth.currentUser
    private var _pasosData: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pasosData: StateFlow<Int?> = _pasosData
    fun getSteps(callback: (Int) -> Unit) {
        fitnessApi.readSteps(activity) { steps ->
            _pasosData.value = steps
            callback(steps)
        }
    }


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



}