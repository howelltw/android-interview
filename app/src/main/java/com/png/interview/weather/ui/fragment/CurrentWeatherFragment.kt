package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.png.interview.databinding.FragmentCurrentWeatherBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.CurrentWeatherFragmentViewBinder
import android.widget.ArrayAdapter

class CurrentWeatherFragment : InjectedFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false).apply {
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_item)
            etInput.setAdapter(arrayAdapter)

            viewBinder = CurrentWeatherFragmentViewBinder(
                getViewModel(),
                requireActivity(),
                settingsAction = {
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToSettingsFragment())
                },
                forecastAction = { query ->
                    findNavController().navigate(
                        CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToForecastFragment(query)
                    )
                }
            )
            this.lifecycleOwner = viewLifecycleOwner

            viewBinder!!.autoCompleteResults.observe(viewLifecycleOwner) { autoCompleteResults ->
                autoCompleteResults?.let {
                    arrayAdapter.clear()
                    arrayAdapter.addAll(autoCompleteResults.map { it.name })
                }
            }

            etInput.setOnItemClickListener { _, _, _, _ ->
                viewBinder!!.goClicked()
            }

        }.root
    }

}