package com.alexyndrik.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexyndrik.weatherapp.data.model.api.OpenweathermapApi
import com.alexyndrik.weatherapp.data.model.api.OpenweathermapRepository
import com.alexyndrik.weatherapp.data.model.openweathermap.forecast.ForecastResponse
import com.alexyndrik.weatherapp.data.model.openweathermap.weather.WeatherResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ForecastViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val repository : OpenweathermapRepository =
        OpenweathermapRepository(OpenweathermapApi.openweathermapApi)

    val weatherLiveData = MutableLiveData<WeatherResponse>()
    val forecastLiveData = MutableLiveData<ForecastResponse>()

    fun getForecast(city: String) {
        scope.launch {
            val weather = repository.getWeather(city)
            weatherLiveData.postValue(weather)
            val forecast = repository.getForecast(city)
            forecastLiveData.postValue(forecast)
        }
    }

    fun getForecast(lat: Float, lon: Float) {
        scope.launch {
            val weather = repository.getWeather(lat, lon)
            weatherLiveData.postValue(weather)
            val forecast = repository.getForecast(lat, lon)
            forecastLiveData.postValue(forecast)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}