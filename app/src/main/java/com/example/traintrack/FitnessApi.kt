package com.example.traintrack

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field

class FitnessApi(private val activity: Activity) {

    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build()
    }

    fun requestPermission(activity: Activity, callback: () -> Unit) {
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(activity), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                activity,
                FITNESS_API_REQUEST_CODE,
                GoogleSignIn.getLastSignedInAccount(activity),
                fitnessOptions
            )
        } else {
            // Permission already granted
            callback.invoke()
        }
    }

    fun readSteps(activity: Activity, callback: (Int) -> Unit) {
        GoogleSignIn.getLastSignedInAccount(activity)?.let {
            Fitness.getHistoryClient(activity, it)
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener { dataSet ->
                    val totalSteps = if (dataSet.isEmpty) 0 else dataSet.dataPoints[0].getValue(
                        Field.FIELD_STEPS).asInt()
                    Log.d(TAG, "Total steps: $totalSteps")
                    callback.invoke(totalSteps)
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error reading steps: ${e.message}", e)
                    callback.invoke(0)
                }
        }
    }

    companion object {
        private const val TAG = "FitnessApi"
        private const val FITNESS_API_REQUEST_CODE = 1001
    }
}