package com.alexyndrik.weatherapp.data.model.openweathermap.forecast

import com.alexyndrik.weatherapp.data.model.openweathermap.common.Clouds
import com.alexyndrik.weatherapp.data.model.openweathermap.common.Weather
import com.alexyndrik.weatherapp.data.model.openweathermap.common.Wind


data class Forecast(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val rain: Rain,
    val snow: Snow,
    val sys: Sys,
    val dt_txt: String
)