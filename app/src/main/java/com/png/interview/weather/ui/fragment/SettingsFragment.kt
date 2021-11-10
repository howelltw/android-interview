package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.png.interview.databinding.FragmentSettingsBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.SettingsFragmentViewBinder
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingsFragment : InjectedFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentSettingsBinding.inflate(inflater, container, false).apply {

            viewBinder = SettingsFragmentViewBinder(
                getViewModel()
            )
            this.lifecycleOwner = viewLifecycleOwner

            viewLifecycleOwner.lifecycleScope.launch {
                viewBinder!!.getTemperatureUnit().observe(viewLifecycleOwner) { unit ->
                    Timber.tag("TESTING").i("TEMP UNIT: $unit")

                    if (unit != null) {
                        viewBinder!!.updateImperialSelectedTempUnit(unit)
                    } else {
                        // Setting a default value
                        viewBinder!!.imperialClicked()
                    }
                }
            }
        }.root
    }

}