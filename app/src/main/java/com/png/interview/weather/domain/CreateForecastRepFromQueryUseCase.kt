package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.ForecastViewData
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import javax.inject.Inject

interface CreateForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String): ForecastViewRepresentation
}

class DefaultCreateForecastRepFromQueryUseCase @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase
) : CreateForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String): ForecastViewRepresentation {
        return when (val result = getForecastDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val forecastList: MutableList<ForecastViewData> = mutableListOf()
                result.body.forecast.forecastday.forEach {
                    forecastList.add(
                        ForecastViewData(
                            date = it.date,
                            minTemperature = "${it.day.mintemp_f} F",
                            maxTemperature = "${it.day.maxtemp_f} F",
                            windSpeed = "${it.day.maxwind_mph} MPH",
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