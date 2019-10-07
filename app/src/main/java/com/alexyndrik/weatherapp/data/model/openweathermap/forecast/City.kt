package com.alexyndrik.weatherapp.data.model.openweathermap.forecast

import com.alexyndrik.weatherapp.data.model.openweathermap.common.Coord

data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String

)