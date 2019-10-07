package com.alexyndrik.weatherapp.data.model.openweathermap.weather

data class Main(
    val temp: Float,
    val pressure: Float,
    val humidity: Int,
    val temp_min: Float,
    val temp_max: Float
)