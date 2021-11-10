package com.png.interview.weather.ui.binder

import androidx.lifecycle.LiveData
import com.png.interview.weather.ui.model.ForecastViewData
import com.png.interview.weather.ui.viewmodel.ForecastViewModel

class ForecastFragmentViewBinder(
    viewModel: ForecastViewModel,
    query: String
) {

    private val availableWeatherViewData = viewModel.availableForecastDataLiveData
    var hasError = viewModel.hasError


    init {
        viewModel.getForecastData(query)
    }

    fun observeData(): LiveData<List<ForecastViewData>?> = availableWeatherViewData

}