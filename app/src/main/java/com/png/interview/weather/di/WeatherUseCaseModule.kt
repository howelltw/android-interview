package com.png.interview.weather.di

import com.png.interview.weather.domain.forecast.*
import com.png.interview.weather.domain.settings.*
import com.png.interview.weather.domain.weather_data.*
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(usecase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(usecase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase

    @Binds
    abstract fun bindsGetAutoCompleteUseCase(usecase: DefaultGetAutoCompleteResultUseCase): GetAutoCompleteResultUseCase

    @Binds
    abstract fun bindsGetAutoCompleteRepUseCase(usecase: DefaultCreateAutoCompleteRepFromQueryUseCase): CreateAutoCompleteRepFromQueryUseCase


    @Binds
    abstract fun bindsGetForecastDataUseCase(usecase: DefaultGetForecastDataUseCase): GetForecastDataUseCase

    @Binds
    abstract fun bindsGetForecastRepUseCase(usecase: DefaultCreateForecastRepFromQueryUseCase): CreateForecastRepFromQueryUseCase

    @Binds
    abstract fun bindsGetTemperatureUnitUseCase(usecase: DefaultGetTemperatureUnitUseCase): GetTemperatureUnitUseCase

    @Binds
    abstract fun bindsUpdateTemperatureUnitUseCase(usecase: DefaultUpdateTemperatureUnitUseCase): UpdateTemperatureUnitUseCase

}