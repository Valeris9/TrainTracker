package com.example.Weather

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder

class WeatherViewModel(context: Context) : ViewModel() {
    private val apiKey = "X7UPRl8FY8Hkip965ss4rghTdsx4pz1t"
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    // Método para obtener el clima actual basado en la ubicación actual del dispositivo
    fun getCurrentWeather(locationKey: String, callback: (String) -> Unit) {
        val url = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/$locationKey?apikey=$apiKey&language=es-es"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val weatherData = parseWeatherData(response)
                callback.invoke(weatherData)
            },
            { error ->
                val errorMessage = error.message ?: "Error desconocido"
                Log.e(TAG, "Error de solicitud: $errorMessage")
            })

        // Agregar log para verificar la URL de la solicitud
        Log.d(TAG, "URL de solicitud: $url")

        requestQueue.add(request)
    }

    fun searchCityByName(cityName: String, callback: (String?) -> Unit) {
        val encodedCityName = URLEncoder.encode(cityName, "UTF-8")
        val url = "https://api.accuweather.com/locations/v1/cities/search?apikey=$apiKey&q=$encodedCityName&language=es-es"

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                val locationKey = extractLocationKey(response)
                callback.invoke(locationKey)
            },
            { error ->
                val errorMessage = error.message ?: "Error desconocido"
                Log.e(TAG, "Error de solicitud: $errorMessage")
                callback.invoke(null)
            })

        requestQueue.add(request)
    }

    private fun extractLocationKey(response: JSONArray): String? {
        if (response.length() > 0) {
            val firstResult = response.optJSONObject(0)
            return firstResult?.optString("Key")
        }
        return null
    }
    private fun parseWeatherData(response: JSONObject): String {
        val headline = response.optJSONObject("Headline")
        val effectiveDate = headline?.optString("EffectiveDate")
        val text = headline?.optString("Text")

        val dailyForecasts = response.optJSONArray("DailyForecasts")
        val forecast = dailyForecasts?.optJSONObject(0)
        val date = forecast?.optString("Date")
        val temperature = forecast?.optJSONObject("Temperature")
        val minimumTemperature = temperature?.optJSONObject("Minimum")?.optInt("Value")
        val maximumTemperature = temperature?.optJSONObject("Maximum")?.optInt("Value")
        val day = forecast?.optJSONObject("Day")
        val dayIconPhrase = day?.optString("IconPhrase")
        val night = forecast?.optJSONObject("Night")
        val nightIconPhrase = night?.optString("IconPhrase")

        // Convertir temperaturas de ºF a ºC
        val minimumTemperatureCelsius = convertFahrenheitToCelsius(minimumTemperature)
        val maximumTemperatureCelsius = convertFahrenheitToCelsius(maximumTemperature)


        val weatherDetails = """
        Temperatura Mínima: $minimumTemperatureCelsius°C
        Temperatura Máxima: $maximumTemperatureCelsius°C
        Tiempo esperado de dia: $dayIconPhrase
        Tiempo esperado de noche: $nightIconPhrase
    """.trimIndent()

        return weatherDetails
    }

    private fun convertFahrenheitToCelsius(temperatureFahrenheit: Int?): Int? {
        if (temperatureFahrenheit != null) {
            return ((temperatureFahrenheit - 32) * 5) / 9
        }
        return null
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}
