package com.png.interview.weather.ui.model

data class ForecastViewData(
    val date: String,
    val minTemperature: String,
    val maxTemperature: String,
    val windSpeed: String,
    val condition: String,
)