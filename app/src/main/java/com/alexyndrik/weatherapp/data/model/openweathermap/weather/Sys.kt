package com.alexyndrik.weatherapp.data.model.openweathermap.weather

data class Sys(
    val type: Int,
    val id: Int,
    val message: Float,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)