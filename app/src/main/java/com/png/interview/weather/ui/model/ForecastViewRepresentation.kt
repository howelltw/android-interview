package com.png.interview.weather.ui.model

sealed class ForecastViewRepresentation {
    class ForecastViewRep(val data: List<ForecastViewData>) : ForecastViewRepresentation()
    object Empty : ForecastViewRepresentation()
    object Error : ForecastViewRepresentation()
}