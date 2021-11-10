package com.png.interview.weather.domain.forecast

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.*
import javax.inject.Inject

interface CreateForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String, days: Int, unit: TemperatureUnit): ForecastViewRepresentation
}

class DefaultCreateForecastRepFromQueryUseCase @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase
) : CreateForecastRepFromQueryUseCase {

    override suspend fun invoke(query: String, days: Int, unit: TemperatureUnit): ForecastViewRepresentation {
        return when (val result = getForecastDataUseCase(query = query, days = days)) {
            is NetworkResponse.Success -> {
                ForecastViewRepresentation.ForecastViewRep(
                    result.body.forecast.forecastday.map {
                        it.toForecastViewData(unit)
                    }
                )
            }
            else -> {
                ForecastViewRepresentation.Error
            }
        }
    }

}