package com.png.interview.weather.di

import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.domain.CreateForecastRepFromQueryUseCase
import com.png.interview.weather.domain.DefaultCreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.domain.DefaultCreateForecastRepFromQueryUseCase
import com.png.interview.weather.domain.DefaultGetCurrentWeatherDataUseCase
import com.png.interview.weather.domain.DefaultGetForecastDataUseCase
import com.png.interview.weather.domain.GetCurrentWeatherDataUseCase
import com.png.interview.weather.domain.GetForecastDataUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(usecase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(usecase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase

    @Binds
    abstract fun bindsGetForecastDataUseCase(usecase: DefaultGetForecastDataUseCase): GetForecastDataUseCase

    @Binds
    abstract fun bindsGetForecastRepUseCase(usecase: DefaultCreateForecastRepFromQueryUseCase): CreateForecastRepFromQueryUseCase
}