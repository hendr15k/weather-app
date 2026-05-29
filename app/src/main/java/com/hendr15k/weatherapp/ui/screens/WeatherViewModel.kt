package com.hendr15k.weatherapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hendr15k.weatherapp.data.WeatherRepository
import com.hendr15k.weatherapp.data.model.WeatherCondition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun onPermissionGranted() {
        _uiState.update { it.copy(hasLocationPermission = true) }
    }

    fun fetchWeather(lat: Double, lon: Double, locationName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, locationName = locationName) }

            repository.getWeather(lat, lon)
                .onSuccess { response ->
                    val current = response.current
                    val daily = response.daily

                    val forecastDays = daily?.time?.indices?.map { i ->
                        val date = LocalDate.parse(daily.time[i])
                        DailyDay(
                            date = daily.time[i],
                            weekday = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN) + ".",
                            condition = WeatherCondition.fromCode(daily.weatherCode.getOrElse(i) { 0 }),
                            tempMax = daily.temperatureMax.getOrElse(i) { 0.0 },
                            tempMin = daily.temperatureMin.getOrElse(i) { 0.0 },
                            precipProb = daily.precipitationProbability?.getOrElse(i) { 0 } ?: 0
                        )
                    } ?: emptyList()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            currentTemp = current?.temperature ?: 0.0,
                            feelsLike = current?.apparentTemperature ?: 0.0,
                            humidity = current?.humidity ?: 0,
                            windSpeed = current?.windSpeed ?: 0.0,
                            condition = WeatherCondition.fromCode(current?.weatherCode ?: 0),
                            dailyForecast = forecastDays,
                            error = null
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = "Fehler: ${e.message ?: "Unbekannt"}")
                    }
                }
        }
    }
}
