package com.example.traintrack

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.Flow

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

        fun obtenerPerfil(userId: String) = callbackFlow<HashMap<String, Any>?> {
            val perfilRef = reference.child("perfiles").child(userId)
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val perfilData = snapshot.getValue<HashMap<String, Any>>()
                    trySend(perfilData).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(null).isSuccess
                }
            }

            perfilRef.addListenerForSingleValueEvent(listener)

            awaitClose { perfilRef.removeEventListener(listener) }
        }

}