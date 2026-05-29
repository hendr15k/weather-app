package com.hendr15k.weatherapp.ui.screens

import com.hendr15k.weatherapp.data.model.DailyForecast
import com.hendr15k.weatherapp.data.model.WeatherCondition

data class WeatherUiState(
    val isLoading: Boolean = true,
    val locationName: String = "",
    val currentTemp: Double = 0.0,
    val feelsLike: Double = 0.0,
    val humidity: Int = 0,
    val windSpeed: Double = 0.0,
    val condition: WeatherCondition = WeatherCondition.UNKNOWN,
    val dailyForecast: List<DailyDay> = emptyList(),
    val error: String? = null,
    val hasLocationPermission: Boolean = false
)

data class DailyDay(
    val date: String,
    val weekday: String,
    val condition: WeatherCondition,
    val tempMax: Double,
    val tempMin: Double,
    val precipProb: Int
)
