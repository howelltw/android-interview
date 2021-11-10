package com.png.interview.weather.domain.weather_data

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.*
import timber.log.Timber
import javax.inject.Inject

interface CreateAutoCompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String): AutoCompleteViewRepresentation
}

class DefaultCreateAutoCompleteRepFromQueryUseCase @Inject constructor(
    private val getAutoCompleteResultUseCase: GetAutoCompleteResultUseCase
) : CreateAutoCompleteRepFromQueryUseCase {

    override suspend fun invoke(query: String): AutoCompleteViewRepresentation {
        return when (val result = getAutoCompleteResultUseCase(query)) {
            is NetworkResponse.Success -> {
                AutoCompleteViewRepresentation.AutoCompleteViewRep(
                    data = result.body.map { AutoCompleteViewData(it.name) }.take(5)
                )
            }
            else -> {
                AutoCompleteViewRepresentation.Error
            }
        }
    }

}