package com.example.traintrack

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseReference {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference = database.reference


    /**
     * Guarda los datos del perfil en la base de datos.
     * @param perfilData Datos del perfil a guardar.
     * @param userId ID del usuario al que pertenece el perfil.
     */
    fun guardarPerfil(perfilData: HashMap<String, Any>, userId: String) {
        // Obtener la referencia del perfil dentro de la base de datos
        val perfilRef = reference.child("perfiles").child(userId)

        // Establecer los datos del perfil en la referencia
        perfilRef.setValue(perfilData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("1","Succesful Guardar perfil")
                } else {
                    Log.d("-1","Error Guardar perfil")
                }
            }
    }

    /**
     * Obtiene los datos del perfil de la base de datos.
     * @param userId ID del usuario al que pertenece el perfil.
     * @param onComplete Función de retorno que se llama cuando se obtienen los datos del perfil.
     *                   Recibe los datos del perfil como parámetro (puede ser nulo si no se encuentran datos).
     */
    fun obtenerPerfil(userId: String, onComplete: (HashMap<String, Any>?) -> Unit) {
        // Obtener la referencia del perfil dentro de la base de datos
        val perfilRef = reference.child("perfiles").child(userId)

        // Agregar un listener para obtener los datos del perfil
        perfilRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Obtener los datos del perfil como un HashMap
                val perfilData = snapshot.value as? HashMap<String, Any>

                // Llamar a la función de retorno con los datos del perfil obtenidos
                onComplete(perfilData)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error en caso de que ocurra
                Log.d("-1", "Error obtener perfil: ${error.message}")

                // Llamar a la función de retorno con valor nulo para indicar que no se encontraron datos
                onComplete(null)
            }
        })
    }


}