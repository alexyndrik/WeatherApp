package com.alexyndrik.weatherapp.data.model.api

import com.alexyndrik.weatherapp.data.model.openweathermap.forecast.ForecastResponse
import com.alexyndrik.weatherapp.data.model.openweathermap.weather.WeatherResponse

class OpenweathermapRepository(private  val api: OpenweathermapApi) : BaseRepository() {

    suspend fun getWeather(city: String) : WeatherResponse? = safeApiCall(
        call = {api.getWeather(city).await()},
        errorMessage = "Error Fetching Weather for $city"
    )

    suspend fun getWeather(lat: Float, lon: Float) : WeatherResponse? = safeApiCall(
        call = {api.getWeather(lat, lon).await()},
        errorMessage = "Error Fetching Weather for $lat,$lon"
    )

    suspend fun getForecast(city: String) : ForecastResponse? = safeApiCall(
        call = {api.getForecast(city).await()},
        errorMessage = "Error Fetching Weather for $city"
    )

    suspend fun getForecast(lat: Float, lon:Float) : ForecastResponse? = safeApiCall(
        call = {api.getForecast(lat, lon).await()},
        errorMessage = "Error Fetching Weather for $lat,$lon"
    )

}