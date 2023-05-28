package com.example.traintrack.Perfil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.traintrack.DatabaseReference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {
    private lateinit var database: DatabaseReference
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: FirebaseUser? = firebaseAuth.currentUser

    // Campos del formulario
    var nombre: String by mutableStateOf("")
    var altura: Float by mutableStateOf(0f)
    var peso: Float by mutableStateOf(0f)
    var edad: Int by mutableStateOf(0)
    var sexo: String by mutableStateOf("")

    // Campos del perfil
    private val _perfilData: MutableStateFlow<HashMap<String, Any>?> = MutableStateFlow(null)
    val perfilData: StateFlow<HashMap<String, Any>?> = _perfilData

    // Método para guardar los datos del perfil en la base de datos
    fun guardarPerfil() {
        currentUser?.let { user ->
            val userId = user.uid

            // Obtener la referencia de la base de datos para el usuario actual
            database = DatabaseReference()

            // Crear un objeto de datos del perfil
            val perfilData: HashMap<String, Any> = hashMapOf(
                "nombre" to nombre,
                "altura" to altura,
                "peso" to peso,
                "edad" to edad,
                "sexo" to sexo
            )

            // Guardar los datos del perfil en la base de datos
            database.guardarPerfil(perfilData, userId)
        }
    }

    fun obtenerPerfil() {
        currentUser?.let { user ->
            val userId = user.uid

            // Obtener la referencia de la base de datos para el usuario actual
            database = DatabaseReference()

            // Obtener perfil desde la base de datos
            database.obtenerPerfil(userId) { perfilData ->
                // Actualizar el StateFlow _perfilData con los datos del perfil obtenidos
                _perfilData.value = perfilData
            }
        }
    }
}
