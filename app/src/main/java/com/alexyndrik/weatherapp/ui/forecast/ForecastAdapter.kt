package com.alexyndrik.weatherapp.ui.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexyndrik.weatherapp.R
import kotlinx.android.synthetic.main.forecast_item.view.*
import java.text.SimpleDateFormat

class ForecastAdapter(val context: Context, var items: ArrayList<ForecastItem>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hour = itemView.hour
        private val hourAverageTemperature = itemView.hour_average_temperature
        private val hourPressure = itemView.hour_pressure
        private val hourHumidity = itemView.hour_humidity
        private val hourWindSpeed = itemView.hour_wind_speed

        private val dateTimeFormat : SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd\nHH:mm:ss")

        fun bind(item: ForecastItem) {
            hour.text = dateTimeFormat.format(item.hour)
            hourAverageTemperature.text = context.getString(R.string.temperature_format).format(item.hourAverageTemperature)
            hourPressure.text = context.getString(R.string.pressure_format).format(item.hourPressure)
            hourHumidity.text = context.getString(R.string.humidity_float_format).format(item.hourHumidity)
            hourWindSpeed.text = context.getString(R.string.wind_speed_format).format(item.hourWindSpeed)
        }
    }

    data class ForecastItem(
        val hour: Long,
        val hourAverageTemperature: Float,
        val hourPressure: Int,
        val hourHumidity: Float,
        val hourWindSpeed: Float
    )


}