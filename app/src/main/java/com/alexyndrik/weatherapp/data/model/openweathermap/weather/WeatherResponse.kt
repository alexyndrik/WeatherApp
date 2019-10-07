package com.alexyndrik.weatherapp.data.model.openweathermap.weather

import com.alexyndrik.weatherapp.data.model.openweathermap.common.Clouds
import com.alexyndrik.weatherapp.data.model.openweathermap.common.Coord
import com.alexyndrik.weatherapp.data.model.openweathermap.common.Weather
import com.alexyndrik.weatherapp.data.model.openweathermap.common.Wind


data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Long,
    val name: String,
    val cod: Int
)