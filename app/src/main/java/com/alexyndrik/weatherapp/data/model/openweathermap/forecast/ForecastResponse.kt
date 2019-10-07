package com.alexyndrik.weatherapp.data.model.openweathermap.forecast

data class ForecastResponse(
    val cod: String,
    val message: Float,
    val cnt: Int,
    val list: List<Forecast>,
    val city: City
)