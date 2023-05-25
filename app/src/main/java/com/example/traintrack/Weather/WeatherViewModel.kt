package com.example.Weather

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class WeatherViewModel(context: Context) : ViewModel() {
    private val apiKey = "X7UPRl8FY8Hkip965ss4rghTdsx4pz1t"
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    // Método para obtener el clima actual basado en la ubicación actual del dispositivo
    fun getCurrentWeather(latitude: Double, longitude: Double, callback: (String) -> Unit) {
        val url = "https://api.accuweather.com/your-endpoint?apikey=$apiKey&latitude=$latitude&longitude=$longitude"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val weatherData = parseWeatherData(response)
                callback.invoke(weatherData)
            },
            { error ->
                val errorMessage = error.message ?: "Error desconocido"
                Log.e(TAG, "Error de solicitud: $errorMessage")            })

        requestQueue.add(request)
    }

    private fun parseWeatherData(response: JSONObject): String {
        val temperature = response.optJSONObject("temperature")?.optInt("value")
        val condition = response.optJSONObject("condition")?.optString("text")
        val humidity = response.optInt("humidity")

        val weatherDetails = "Temperature: $temperature°C\nCondition: $condition\nHumidity: $humidity%"
        return weatherDetails
    }
}
