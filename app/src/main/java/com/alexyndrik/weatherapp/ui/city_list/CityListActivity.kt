package com.alexyndrik.weatherapp.ui.city_list

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alexyndrik.weatherapp.*
import com.alexyndrik.weatherapp.data.model.openweathermap.weather.WeatherResponse
import com.alexyndrik.weatherapp.ui.forecast.ForecastActivity
import com.alexyndrik.weatherapp.ui.AddNewCityActivity
import com.alexyndrik.weatherapp.viewmodel.CityListViewModel
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList

class CityListActivity : AppCompatActivity() {

    companion object {
        const val ADD_NEW_CITY_REQUEST = 7
    }

    private lateinit var cities : TreeSet<String>

    private val cityListViewModel: CityListViewModel by lazy {
        ViewModelProviders.of(this).get(CityListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cities = TreeSet((listOf("Moscow", "Saint Petersburg")))

        initRecycleView(cities.toList())

        main_add_new_city_btn.setOnClickListener {
            val intent = Intent(this@CityListActivity, AddNewCityActivity::class.java)
            startActivityForResult(intent, ADD_NEW_CITY_REQUEST)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_NEW_CITY_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    println("result")
                    if (data.hasExtra("city")
                        && data.getStringExtra("city") != null
                        && data.getStringExtra("city")?.isNotEmpty()!!
                    ) {
                        getCityItem(data.getStringExtra("city"))
                    } else if (data.hasExtra("lat") && data.hasExtra("lon")) {
                        println(data.getFloatExtra("lat", -0.0f))
                        println(data.getFloatExtra("lon", -0.0f))
                        getCityItem(
                            data.getFloatExtra("lat", -0.0f),
                            data.getFloatExtra("lon", -0.0f)
                        )
                    }
                }
            }
        }
    }

    private fun initRecycleView(cityList: List<String?>) {
        startObserve()
        getCityItems(cityList)
    }

    private fun getCityItems(cityList: List<String?>) {
        cityList.forEach { c ->
            if (c != null) {
                cityListViewModel.getWeather(c)
            }
        }
    }

    private fun getCityItem(city: String?) {
        if (city != null) {
            cityListViewModel.getWeather(city)
        }
    }

    private fun getCityItem(lat: Float?, lon: Float?) {
        if (lat != null && lon != null) {
            cityListViewModel.getWeather(lat, lon)
        }
    }

    private fun startObserve() {
        cityListViewModel.weatherLiveData.observe(this, Observer<MutableList<WeatherResponse>> { cityItems ->
            val items = ArrayList<CityAdapter.CityItem>()
            cityItems.forEach { item ->
                cities.add(item.name)
                items.add(
                    CityAdapter.CityItem(
                    item.name,
                    item.coord.lat,
                    item.coord.lon,
                    item.main.temp,
                    item.main.humidity
                ))
            }

            prepareRecycleView(items)
        })
    }

    private fun prepareRecycleView(cityItemList: ArrayList<CityAdapter.CityItem>) {
        val cityAdapter = CityAdapter(
            this,
            cityItemList,
            object : CityAdapter.Callback {
                override fun onItemClicked(item: CityAdapter.CityItem) {
                    val intent = Intent(this@CityListActivity, ForecastActivity::class.java)
                    intent.putExtra("certain_city", item.cityName)
                    startActivity(intent)
                }
            })
        city_list.setHasFixedSize(true)
        city_list.adapter = cityAdapter
        city_list.adapter?.notifyDataSetChanged()
    }

}
