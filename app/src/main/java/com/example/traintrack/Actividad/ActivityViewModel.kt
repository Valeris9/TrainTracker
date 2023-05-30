package com.example.traintrack.Actividad

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.traintrack.FitnessApi

class ActivityViewModel(private val fitnessApi: FitnessApi, private val activity: Activity) : ViewModel() {

    fun getSteps(callback: (Int) -> Unit) {
        fitnessApi.readSteps(activity,callback)
    }
}