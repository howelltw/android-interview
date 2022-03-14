package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateForecastRepFromQueryUseCase
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val createForecastRepFromQueryUseCase: CreateForecastRepFromQueryUseCase
) : ViewModel() {

    private val _forecastViewRepresentation = MutableStateFlow<ForecastViewRepresentation>(ForecastViewRepresentation.Empty)

    fun submitForecastSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _forecastViewRepresentation.value = createForecastRepFromQueryUseCase(query)
        }
    }

    val availableForecastLiveData =
        _forecastViewRepresentation
            .map { (it as? ForecastViewRepresentation.ForecastViewRep)?.data }
            .asLiveData()

    val isErrorVisible =
        _forecastViewRepresentation
            .map { it is ForecastViewRepresentation.Error }
            .asLiveData()
            .value
}