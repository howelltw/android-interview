package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.png.interview.databinding.FragmentCurrentWeatherBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.AutocompleteViewBinder
import com.png.interview.weather.ui.binder.CurrentWeatherFragmentViewBinder
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_current_weather.view.*
import timber.log.Timber

class CurrentWeatherFragment : InjectedFragment() {
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentCurrentWeatherBinding.inflate(inflater, container,false).apply {
            viewBinder = CurrentWeatherFragmentViewBinder(
                getViewModel(),
                requireActivity(),
                forecastAction = { cityCode ->
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToForecastFragment(cityCode))
                },
                settingsAction = {
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToSettingsFragment())
                }
            )
            this.lifecycleOwner = viewLifecycleOwner
            this.rvCityAutocomplete.adapter = groupAdapter

            this.etInput.doAfterTextChanged { partialCity ->
                if (partialCity == null || partialCity.length < 3) {
                    return@doAfterTextChanged
                }
                this.autocompleteWrapper.isVisible = true
                getViewModel<CurrentWeatherViewModel>().submitAutocompleteSearch(partialCity.toString())
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel<CurrentWeatherViewModel>().availableAutocompleteLiveData.observe(viewLifecycleOwner) {
            val autocompleteList: MutableList<AutocompleteViewBinder> = ArrayList()
            it?.forEach { autocompleteViewData ->
                Timber.d("autocompleteViewData = $autocompleteViewData")
                autocompleteList.add(AutocompleteViewBinder(autocompleteViewData))
            }
            groupAdapter.updateAsync(autocompleteList)
        }

        groupAdapter.setOnItemClickListener { item, _ ->
            (item as? AutocompleteViewBinder)?.let {
                getViewModel<CurrentWeatherViewModel>().savedInput = it.autocompleteViewData.url

                // Hide the autocomplete list, and clear the EditText before submitting the search
                view.autocomplete_wrapper.isVisible = false
                view.et_input.text.clear()
                getViewModel<CurrentWeatherViewModel>().submitCurrentWeatherSearch(it.autocompleteViewData.url)
            }
        }
    }
}