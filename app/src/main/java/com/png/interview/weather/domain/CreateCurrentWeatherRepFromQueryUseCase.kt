package com.png.interview.weather.domain

import android.content.SharedPreferences
import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.binder.SettingsFragmentViewBinder
import com.png.interview.weather.ui.model.AvailableWeatherViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import javax.inject.Inject

interface CreateCurrentWeatherRepFromQueryUseCase {
    suspend operator fun invoke(query: String): CurrentWeatherViewRepresentation
}

class DefaultCreateCurrentWeatherRepFromQueryUseCase @Inject constructor(
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase,
    private val sharedPreferences: SharedPreferences,
) : CreateCurrentWeatherRepFromQueryUseCase {
    override suspend fun invoke(query: String): CurrentWeatherViewRepresentation {
        val isMetric = sharedPreferences.getBoolean(SettingsFragmentViewBinder.IS_METRIC, false)

        return when (val result = getCurrentWeatherDataUseCase(query)) {
            is NetworkResponse.Success -> {
                CurrentWeatherViewRepresentation.AvailableWeatherViewRep(
                    AvailableWeatherViewData(
                        name = result.body.location.name,
                        date = result.body.location.localtime,
                        temperature = if (isMetric) "${result.body.current.temp_c} C" else "${result.body.current.temp_f} F",
                        condition = result.body.current.condition.text,
                        windDirection = result.body.current.wind_dir,
                        windSpeed = if (isMetric) "${result.body.current.gust_kph} KPH" else "${result.body.current.gust_mph} MPH"
                    )
                )
            }
            else -> {
                CurrentWeatherViewRepresentation.Error
            }
        }
    }
}