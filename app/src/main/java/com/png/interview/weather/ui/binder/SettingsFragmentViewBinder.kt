package com.png.interview.weather.ui.binder

import android.content.SharedPreferences

class SettingsFragmentViewBinder (
    private val sharedPreferences: SharedPreferences,
) {
    fun onMetricClicked (isMetric: Boolean) {
        val prefEditor = sharedPreferences.edit()
        prefEditor.putBoolean(IS_METRIC, isMetric)
        prefEditor.apply()
    }

    fun isMetric() = sharedPreferences.getBoolean(IS_METRIC, false)

    companion object {
        const val IS_METRIC: String = "IS_METRIC"
    }
}