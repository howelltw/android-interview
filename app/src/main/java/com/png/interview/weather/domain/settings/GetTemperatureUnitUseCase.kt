package com.png.interview.weather.domain.settings

import com.png.interview.weather.pref.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface GetTemperatureUnitUseCase {
    suspend operator fun invoke(): Flow<String?>
}

class DefaultGetTemperatureUnitUseCase @Inject constructor(
    private val userPreferences: UserPreferences
) : GetTemperatureUnitUseCase {
    override suspend fun invoke(): Flow<String?> {
        return userPreferences.temperatureUnit
    }
}