package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateAutocompleteRepFromQueryUseCase
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutocompleteRepFromQueryUseCase: CreateAutocompleteRepFromQueryUseCase,
) : ViewModel() {

    var savedInput: String = ""
    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autocompleteViewRepresentation = MutableStateFlow<AutocompleteViewRepresentation>(AutocompleteViewRepresentation.Empty)

    fun submitCurrentWeatherSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query)
        }
    }

    fun submitAutocompleteSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _autocompleteViewRepresentation.value = createAutocompleteRepFromQueryUseCase(query)
        }
    }

    fun resetCurrentWeatherState() {
        _currentWeatherViewRepresentation.value = CurrentWeatherViewRepresentation.Empty
    }

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map { (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data }
            .asLiveData()

    val availableAutocompleteLiveData =
        _autocompleteViewRepresentation
            .map { (it as? AutocompleteViewRepresentation.AutocompleteViewRep)?.data }
            .asLiveData()

    val isEmptyVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Empty }
            .asLiveData()

    val isErrorVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Error }
            .asLiveData()
}