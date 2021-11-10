package com.png.interview.weather.ui.binder

import androidx.lifecycle.LiveData
import com.png.interview.weather.ui.model.TemperatureUnit
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class SettingsFragmentViewBinder(
    private val viewModel: SettingsViewModel
) {

    var isImperialSelected: LiveData<Boolean> = viewModel.isImperialSelected


    suspend fun getTemperatureUnit(): LiveData<String?> {
        return viewModel.getTemperatureUnit()
    }

    fun updateImperialSelectedTempUnit(unit: String) {
        viewModel.updateImperialSelectedTempUnit(unit)
    }

    fun imperialClicked() {
        viewModel.updateTemperatureUnitInApp(TemperatureUnit.Imperial)
    }

    fun metricClicked() {
        viewModel.updateTemperatureUnitInApp(TemperatureUnit.Metric)
    }

}