package com.png.interview.weather.ui.binder

import android.view.View
import com.png.interview.R
import com.png.interview.databinding.ViewForecastBinding
import com.png.interview.weather.ui.model.ForecastViewData
import com.xwray.groupie.viewbinding.BindableItem

class ForecastViewBinder(
    private val forecastViewData: ForecastViewData,
) : BindableItem<ViewForecastBinding>() {

    override fun bind(viewBinding: ViewForecastBinding, position: Int) { }

    override fun getLayout() = R.layout.view_forecast

    override fun initializeViewBinding(view: View) = ViewForecastBinding.bind(view).apply { viewData = forecastViewData }
}