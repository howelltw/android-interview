package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: (String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val autoCompleteResults = viewModel.autoCompleteResults
    val isEmpty = viewModel.isEmptyVisible
    var hasError = viewModel.hasError

    var input: String = ""

    fun refreshClicked() {
        availableWeatherViewData.value?.name?.let { viewModel.submitCurrentWeatherSearch(it) }
    }

    fun seeForecastClicked() {
        availableWeatherViewData.value?.name?.let { forecastAction(it) }
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun afterInputTextChanged() {
        if (input.length >= 3) {
            viewModel.getAutoCompleteResults(input)
        }
    }

    fun goClicked() {
        when {
            input.isEmpty() -> {
                Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
            }
            input.length < 3 -> {
                Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
            }
            else -> {
                viewModel.submitCurrentWeatherSearch(input)
            }
        }
    }

}