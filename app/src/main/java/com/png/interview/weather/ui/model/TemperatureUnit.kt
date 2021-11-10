package com.png.interview.weather.ui.model

sealed class TemperatureUnit(val unit: String) {
    object Imperial : TemperatureUnit("Imperial")
    object Metric : TemperatureUnit("Metric")
}

fun fromStringToTemperatureUnit(unit: String?): TemperatureUnit {
    return when (unit) {
        TemperatureUnit.Imperial.unit -> TemperatureUnit.Imperial
        TemperatureUnit.Metric.unit -> TemperatureUnit.Metric
        else -> TemperatureUnit.Imperial
    }
}