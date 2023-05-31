package com.example.traintrack

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

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
        val calendar = Calendar.getInstance()
        calendar.time = Date()

        val endTime = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1) // Retrocede un día
        val startTime = calendar.timeInMillis

        val request = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .build()

        GoogleSignIn.getLastSignedInAccount(activity)?.let {
            Fitness.getHistoryClient(activity, it)
                .readData(request)
                .addOnSuccessListener { response ->
                    var totalSteps = 0
                    response.buckets.forEach { bucket ->
                        bucket.dataSets.forEach { dataSet ->
                            dataSet.dataPoints.forEach { dataPoint ->
                                val steps = dataPoint.getValue(Field.FIELD_STEPS).asInt()
                                totalSteps += steps
                            }
                        }
                    }
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