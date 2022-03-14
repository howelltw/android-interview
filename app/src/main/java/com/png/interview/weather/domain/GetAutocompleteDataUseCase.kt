package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.AutocompleteResponseItem
import javax.inject.Inject

interface GetAutocompleteDataUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<List<AutocompleteResponseItem>, Unit>
}

class DefaultGetAutocompleteDataUseCase @Inject constructor(
    private val weatherApi: WeatherApi
) : GetAutocompleteDataUseCase {
    override suspend fun invoke(query: String): NetworkResponse<List<AutocompleteResponseItem>, Unit> {
        return weatherApi.getAutocompleteResults(query)
    }
}