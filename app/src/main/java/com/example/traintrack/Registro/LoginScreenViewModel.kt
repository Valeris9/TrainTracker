package com.example.traintrack.Registro

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    /**
     * Inicia sesión utilizando las credenciales de Google.
     * Una vez completado el inicio de sesión, se llama a la función [home].
     */
    @SuppressLint("SuspiciousIndentation")
    fun signInWithGoogleCredential(credential:AuthCredential, home:()->Unit)
    = viewModelScope.launch{
        try {
            // Iniciar sesión con las credenciales proporcionadas

            auth.signInWithCredential(credential)
                .addOnCompleteListener { task->
                    if(task.isSuccessful)
                    Log.d("TrainTrack", "Logueado con exito")
                    // Llamar a la función [home] después de iniciar sesión

                    home()
                }.addOnFailureListener{
                    Log.d("TrainTrack", "Fallo al loguear")
            }
        }catch (ex: Exception){
            Log.d("TrainTrack", "Excepcion al loguear" + "${ex.localizedMessage}" )
        }


    }





}
