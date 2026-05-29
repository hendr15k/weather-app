package com.hendr15k.weatherapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("current")
    val current: CurrentWeather? = null,
    @SerialName("daily")
    val daily: DailyForecast? = null
)

@Serializable
data class CurrentWeather(
    val time: String,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("relative_humidity_2m")
    val humidity: Int,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("wind_speed_10m")
    val windSpeed: Double,
    @SerialName("apparent_temperature")
    val apparentTemperature: Double
)

@Serializable
data class DailyForecast(
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCode: List<Int>,
    @SerialName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerialName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerialName("precipitation_probability_max")
    val precipitationProbability: List<Int>? = null,
    @SerialName("sunrise")
    val sunrise: List<String>? = null,
    @SerialName("sunset")
    val sunset: List<String>? = null
)
