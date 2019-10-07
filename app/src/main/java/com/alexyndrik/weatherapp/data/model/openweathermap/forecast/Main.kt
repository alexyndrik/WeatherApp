package com.alexyndrik.weatherapp.data.model.openweathermap.forecast

data class Main(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val sea_level: Float,
    val grnd_lovel: Float,
    val humidity: Int,
    val temp_kf: Float
)