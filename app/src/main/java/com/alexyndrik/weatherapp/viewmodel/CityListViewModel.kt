package com.alexyndrik.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexyndrik.weatherapp.data.model.api.OpenweathermapApi
import com.alexyndrik.weatherapp.data.model.api.OpenweathermapRepository
import com.alexyndrik.weatherapp.data.model.openweathermap.weather.WeatherResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CityListViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val repository : OpenweathermapRepository =
        OpenweathermapRepository(OpenweathermapApi.openweathermapApi)

    val weatherLiveData = MutableLiveData<MutableList<WeatherResponse>>()

    fun getWeather(city: String) {
        scope.launch {
            val weather = repository.getWeather(city)
            val value = weatherLiveData.value ?: mutableListOf()
            weather?.let { value.add(it) }
            weatherLiveData.postValue(value)
        }
    }

    fun getWeather(lat: Float, lon: Float) {
        scope.launch {
            val weather = repository.getWeather(lat, lon)
            val value = weatherLiveData.value ?: mutableListOf()
            weather?.let { value.add(it) }
            weatherLiveData.postValue(value)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}