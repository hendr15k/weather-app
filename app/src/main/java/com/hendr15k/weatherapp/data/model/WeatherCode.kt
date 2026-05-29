package com.hendr15k.weatherapp.data.model

enum class WeatherCondition(val description: String, val icon: String) {
    CLEAR("Klar", "☀️"),
    PARTLY_CLOUDY("Teilweise bewölkt", "⛅"),
    CLOUDY("Bewölkt", "☁️"),
    FOG("Nebel", "🌫️"),
    DRIZZLE("Nieselregen", "🌦️"),
    RAIN("Regen", "🌧️"),
    HEAVY_RAIN("Starkregen", "⛈️"),
    SNOW("Schnee", "🌨️"),
    THUNDERSTORM("Gewitter", "⛈️"),
    UNKNOWN("Unbekannt", "❓");

    companion object {
        fun fromCode(code: Int): WeatherCondition = when (code) {
            0 -> CLEAR
            1, 2, 3     -> PARTLY_CLOUDY
            45, 48      -> FOG
            51, 53, 55  -> DRIZZLE
            61, 63 -> RAIN
            65, 80, 81  -> HEAVY_RAIN
            71, 73, 75, 77, 85, 86 -> SNOW
            95, 96, 99  -> THUNDERSTORM
            else        -> CLOUDY
        }
    }
}
