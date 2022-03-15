package com.png.interview.weather.domain

import android.content.SharedPreferences
import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.binder.SettingsFragmentViewBinder
import com.png.interview.weather.ui.model.ForecastViewData
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import javax.inject.Inject

interface CreateForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String): ForecastViewRepresentation
}

class DefaultCreateForecastRepFromQueryUseCase @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase,
    private val sharedPreferences: SharedPreferences,
) : CreateForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String): ForecastViewRepresentation {
        val isMetric = sharedPreferences.getBoolean(SettingsFragmentViewBinder.IS_METRIC, false)

        return when (val result = getForecastDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val forecastList: MutableList<ForecastViewData> = mutableListOf()
                result.body.forecast.forecastday.forEach {
                    forecastList.add(
                        ForecastViewData(
                            date = it.date,
                            minTemperature = if (isMetric) "${it.day.mintemp_c} C" else "${it.day.mintemp_f} F",
                            maxTemperature = if (isMetric) "${it.day.maxtemp_c} C" else "${it.day.maxtemp_f} F",
                            windSpeed = if (isMetric) "${it.day.maxwind_kph} KPH" else "${it.day.maxwind_mph} MPH",
                            condition = it.day.condition.text,
                        )
                    )
                }
                ForecastViewRepresentation.ForecastViewRep(forecastList)
            }
            else -> {
                ForecastViewRepresentation.Error
            }
        }
    }
}