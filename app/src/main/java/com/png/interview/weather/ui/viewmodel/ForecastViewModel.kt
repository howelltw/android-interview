package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.forecast.CreateForecastRepFromQueryUseCase
import com.png.interview.weather.domain.settings.GetTemperatureUnitUseCase
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import com.png.interview.weather.ui.model.fromStringToTemperatureUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val createForecastRepFromQueryUseCase: CreateForecastRepFromQueryUseCase
) : ViewModel() {

    private val _forecastViewRepresentation = MutableStateFlow<ForecastViewRepresentation>(ForecastViewRepresentation.Empty)

    fun getForecastData(query: String) {
        viewModelScope.launch {
            val tempUnitValue = getTemperatureUnitUseCase().first()

            _forecastViewRepresentation.value = createForecastRepFromQueryUseCase(query, days = 3, unit = fromStringToTemperatureUnit(tempUnitValue))
        }
    }

    val availableForecastDataLiveData =
        _forecastViewRepresentation
            .map { (it as? ForecastViewRepresentation.ForecastViewRep)?.data }
            .asLiveData()

    val hasError =
        _forecastViewRepresentation
            .map { (it is ForecastViewRepresentation.Error) }
            .asLiveData()

}