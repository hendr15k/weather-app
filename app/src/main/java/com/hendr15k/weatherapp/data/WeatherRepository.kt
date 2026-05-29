package com.hendr15k.weatherapp.data

import com.hendr15k.weatherapp.data.api.RetrofitClient
import com.hendr15k.weatherapp.data.model.WeatherResponse

class WeatherRepository {
    private val api = RetrofitClient.weatherApi

    suspend fun getWeather(lat: Double, lon: Double): Result<WeatherResponse> {
        return try {
            val response = api.getWeather(latitude = lat, longitude = lon)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
