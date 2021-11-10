package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.settings.GetTemperatureUnitUseCase
import com.png.interview.weather.domain.weather_data.CreateAutoCompleteRepFromQueryUseCase
import com.png.interview.weather.domain.weather_data.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutoCompleteViewRepresentation
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import com.png.interview.weather.ui.model.fromStringToTemperatureUnit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutoCompleteRepFromQueryUseCase: CreateAutoCompleteRepFromQueryUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autoCompleteResultsViewRepresentation = MutableStateFlow<AutoCompleteViewRepresentation>(AutoCompleteViewRepresentation.Empty)


    fun getAutoCompleteResults(query: String) {
        viewModelScope.launch {
            delay(300)
            _autoCompleteResultsViewRepresentation.value = createAutoCompleteRepFromQueryUseCase(query)
        }
    }

    fun submitCurrentWeatherSearch(query: String) {
        viewModelScope.launch {
            val tempUnitValue = getTemperatureUnitUseCase().first()
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query, fromStringToTemperatureUnit(tempUnitValue))
        }
    }

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map { (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data }
            .asLiveData()

    val isEmptyVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Empty }
            .asLiveData()

    val hasError =
        _currentWeatherViewRepresentation
            .map { (it is CurrentWeatherViewRepresentation.Error) }
            .asLiveData()

    val autoCompleteResults = _autoCompleteResultsViewRepresentation
        .map { (it as? AutoCompleteViewRepresentation.AutoCompleteViewRep)?.data }
        .asLiveData()


}