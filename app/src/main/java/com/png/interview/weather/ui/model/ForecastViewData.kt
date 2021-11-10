package com.png.interview.weather.ui.model

import com.png.interview.weather.api.model.Forecastday

data class ForecastViewData(
    val date: String,
    val minTemperature: String,
    val maxTemperature: String,
    val windSpeed: String,
    val condition: String
)

fun Forecastday.toForecastViewData(unit: TemperatureUnit): ForecastViewData = ForecastViewData(
    date = date,
    minTemperature = if (unit == TemperatureUnit.Imperial) "${day.mintemp_f} F" else "${day.mintemp_c} C",
    maxTemperature = if (unit == TemperatureUnit.Imperial) "${day.maxtemp_f} F" else "${day.maxtemp_c} C",
    windSpeed = if (unit == TemperatureUnit.Imperial) "${day.maxwind_mph} MPH" else "${day.maxwind_kph} KPH",
    condition = day.condition.text
)