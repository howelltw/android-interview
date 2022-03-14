package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.png.interview.databinding.FragmentForecastBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.ForecastViewBinder
import com.png.interview.weather.ui.viewmodel.ForecastViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class ForecastFragment : InjectedFragment() {
    private val args: ForecastFragmentArgs by navArgs()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        getViewModel<ForecastViewModel>().availableForecastLiveData.observe(viewLifecycleOwner) {
            val forecastViewList: MutableList<ForecastViewBinder> = ArrayList()
            it?.forEach { forecastViewData ->
                forecastViewList.add(ForecastViewBinder(forecastViewData))
            }
            groupAdapter.updateAsync(forecastViewList)
        }

        getViewModel<ForecastViewModel>().submitForecastSearch(args.cityCode)
        return FragmentForecastBinding.inflate(inflater, container,false).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.rvForecast.adapter = groupAdapter
        }.root
    }
}