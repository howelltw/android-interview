package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import javax.inject.Inject

interface CreateAutocompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String): AutocompleteViewRepresentation
}

class DefaultCreateAutocompleteRepFromQueryUseCase @Inject constructor(
    private val getAutocompleteDataUseCase: GetAutocompleteDataUseCase
) : CreateAutocompleteRepFromQueryUseCase {
    override suspend fun invoke(query: String): AutocompleteViewRepresentation {
        return when (val result = getAutocompleteDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val autocompleteList: MutableList<AutocompleteViewData> = mutableListOf()
                result.body.forEach {
                    autocompleteList.add(
                        AutocompleteViewData(
                            locationName = "${it.name}, ${it.region}, ${it.country}",
                            url = it.url
                        )
                    )
                }
                AutocompleteViewRepresentation.AutocompleteViewRep(autocompleteList)
            }
            else -> {
                AutocompleteViewRepresentation.Error
            }
        }
    }
}