package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    var input: String = ""
    var savedInput: String = ""

    fun refreshClicked() {
        if (savedInput.isNotBlank()) {
            viewModel.submitCurrentWeatherSearch(savedInput)
        } else {
            goClicked()
        }
    }

    fun seeForecastClicked() {
        Toast.makeText(activity, "Forecast Clicked TODO", Toast.LENGTH_LONG).show()
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