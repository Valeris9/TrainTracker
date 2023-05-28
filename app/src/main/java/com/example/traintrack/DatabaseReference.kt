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

    fun guardarPerfil(perfilData: HashMap<String, Any>, userId: String) {
        val perfilRef = reference.child("perfiles").child(userId)
        perfilRef.setValue(perfilData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("1","Succesful Guardar perfil")
                } else {
                    Log.d("-1","Error Guardar perfil")
                }
            }
    }

    fun obtenerPerfil(userId: String, onComplete: (HashMap<String, Any>?) -> Unit) {
        val perfilRef = reference.child("perfiles").child(userId)
        perfilRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val perfilData = snapshot.value as? HashMap<String, Any>
                onComplete(perfilData)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error en caso de que ocurra
                Log.d("-1", "Error obtener perfil: ${error.message}")
                onComplete(null)
            }
        })
    }


}