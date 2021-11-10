package com.png.interview.weather.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val USER_DATABASE_NAME = "user_datastore"
        private val KEY_TEMPERATURE_UNIT_ENABLED = stringPreferencesKey("temperature_unit")
    }

    private val Context.prefDatastore: DataStore<Preferences> by preferencesDataStore(name = USER_DATABASE_NAME)
    private val dataStore: DataStore<Preferences>
        get() = context.prefDatastore


    val temperatureUnit: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_TEMPERATURE_UNIT_ENABLED]
        }

    suspend fun updateTemperatureUnit(unit: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[KEY_TEMPERATURE_UNIT_ENABLED] = unit
        }
    }

    suspend fun clear() {
        dataStore.edit { mutablePreferences ->
            mutablePreferences.clear()
        }
    }

}