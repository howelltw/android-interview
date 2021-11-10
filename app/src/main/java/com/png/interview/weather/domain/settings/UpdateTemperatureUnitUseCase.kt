package com.png.interview.weather.domain.settings

import com.png.interview.weather.pref.UserPreferences
import com.png.interview.weather.ui.model.TemperatureUnit
import javax.inject.Inject

interface UpdateTemperatureUnitUseCase {
    suspend operator fun invoke(updatedUnit: TemperatureUnit)
}

class DefaultUpdateTemperatureUnitUseCase @Inject constructor(
    private val userPreferences: UserPreferences
) : UpdateTemperatureUnitUseCase {
    override suspend fun invoke(updatedUnit: TemperatureUnit) {
        userPreferences.updateTemperatureUnit(updatedUnit.unit)
    }
}