package com.alexyndrik.weatherapp.ui.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alexyndrik.weatherapp.R
import com.alexyndrik.weatherapp.data.model.openweathermap.forecast.ForecastResponse
import com.alexyndrik.weatherapp.data.model.openweathermap.weather.WeatherResponse
import com.alexyndrik.weatherapp.viewmodel.ForecastViewModel
import kotlinx.android.synthetic.main.certain_city_layout.*
import kotlinx.android.synthetic.main.city_info.view.*
import kotlin.collections.ArrayList

class ForecastActivity : AppCompatActivity() {

    private val forecastViewModel: ForecastViewModel by lazy {
        ViewModelProviders.of(this).get(ForecastViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.certain_city_layout)

        hourly_forecast.apply {
            setHasFixedSize(true)
        }

        val certainCity = intent.getStringExtra("certain_city")

        forecastViewModel.getForecast(certainCity)
        forecastViewModel.weatherLiveData.observe(this, Observer<WeatherResponse> { weather ->
            certain_city_name.text = weather.name
            certain_city_info.city_lat.text= getString(R.string.latitude_format).format(weather.coord.lat)
            certain_city_info.city_lng.text = getString(R.string.longitude_format).format(weather.coord.lon)
            certain_city_info.city_temperature.text = getString(R.string.temperature_format).format(weather.main.temp)
            certain_city_info.city_humidity.text = getString(R.string.humidity_format).format(weather.main.humidity)
        })
        forecastViewModel.forecastLiveData.observe(this, Observer<ForecastResponse> { forecast ->
            val items = ArrayList<ForecastAdapter.ForecastItem>()
            forecast.list.forEach { item ->
                items.add(
                    ForecastAdapter.ForecastItem(
                        item.dt*1000,
                        item.main.temp,
                        item.main.humidity,
                        item.main.pressure,
                        item.wind.speed
                    )
                )
            }

            val hourlyForecastAdapter =
                ForecastAdapter(this, items)

            hourly_forecast.adapter = hourlyForecastAdapter
        })
    }

}
