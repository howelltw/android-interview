package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val forecastAction: (String) -> Unit,
    private val settingsAction: () -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    var input: String = ""
    // This will not survive config change, etc., but works for the simple case.
    var savedInput: String = ""

    fun refreshClicked() {
        if (savedInput.isNotBlank()) {
            viewModel.submitCurrentWeatherSearch(savedInput)
        } else {
            goClicked()
        }
    }

    fun seeForecastClicked() {
        if (savedInput.isNotBlank()) {
            forecastAction(savedInput)
        } else {
            goClicked()
        }
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        // This will reset any previous error states back to a more appropriate 'Empty' state.
        viewModel.resetCurrentWeatherState()

        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
        } else {
            savedInput = input
            viewModel.submitCurrentWeatherSearch(input)
        }
    }
}