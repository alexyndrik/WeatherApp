package com.alexyndrik.weatherapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexyndrik.weatherapp.R
import kotlinx.android.synthetic.main.add_new_city_layout.*

class AddNewCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_city_layout)

        add_new_city_btn.setOnClickListener {
            val new_city_name = new_city_name.text?.toString()
            val new_city_lat = new_city_lat.text?.toString()?.toFloatOrNull()
            val new_city_lon = new_city_lon?.text?.toString()?.toFloatOrNull()

            println("$new_city_name")
            println("$new_city_lat - $new_city_lon")

            val data = Intent()
            data.putExtra("city", new_city_name)
            data.putExtra("lat", new_city_lat)
            data.putExtra("lon", new_city_lon)

            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}