package com.alexyndrik.weatherapp.data.model.openweathermap.common

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)