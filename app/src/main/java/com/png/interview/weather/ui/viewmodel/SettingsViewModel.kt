package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.*
import com.png.interview.weather.domain.settings.GetTemperatureUnitUseCase
import com.png.interview.weather.domain.settings.UpdateTemperatureUnitUseCase
import com.png.interview.weather.ui.model.TemperatureUnit
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val updateTemperatureUnitUseCase: UpdateTemperatureUnitUseCase
) : ViewModel() {

    private val _isImperialSelected: MutableLiveData<Boolean> = MutableLiveData()
    val isImperialSelected: LiveData<Boolean>
        get() = _isImperialSelected

    fun updateImperialSelectedTempUnit(unit: String) {
        when (unit) {
            TemperatureUnit.Imperial.unit -> _isImperialSelected.value = true
            TemperatureUnit.Metric.unit -> _isImperialSelected.value = false
            else -> _isImperialSelected.value = true
        }
    }

    suspend fun getTemperatureUnit(): LiveData<String?> {
        return getTemperatureUnitUseCase().asLiveData()
    }

    fun updateTemperatureUnitInApp(updatedUnit: TemperatureUnit) {
        viewModelScope.launch {
            updateTemperatureUnitUseCase(updatedUnit)
        }
    }

}