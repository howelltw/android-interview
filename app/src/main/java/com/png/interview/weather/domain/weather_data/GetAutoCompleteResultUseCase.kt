package com.png.interview.weather.domain.weather_data

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.AutcompleteResponseItem
import com.png.interview.weather.api.model.AutoCompleteResponse
import com.png.interview.weather.api.model.CurrentWeatherResponse
import javax.inject.Inject

interface GetAutoCompleteResultUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<List<AutcompleteResponseItem>, Unit>
}

class DefaultGetAutoCompleteResultUseCase @Inject constructor(
    private val weatherApi: WeatherApi
) : GetAutoCompleteResultUseCase {
    override suspend fun invoke(query: String): NetworkResponse<List<AutcompleteResponseItem>, Unit> {
        return weatherApi.getAutocompleteResults(query)
    }
}