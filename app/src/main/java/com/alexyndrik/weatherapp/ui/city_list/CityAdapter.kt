package com.alexyndrik.weatherapp.ui.city_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexyndrik.weatherapp.R
import kotlinx.android.synthetic.main.city_info.view.*
import kotlinx.android.synthetic.main.city_item.view.*

class CityAdapter(val context: Context, var items: ArrayList<CityItem>, val callback: Callback) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName = itemView.city_name
        private val cityLat = itemView.city_lat
        private val cityLng = itemView.city_lng
        private val cityTemperature = itemView.city_temperature
        private val cityHumidity = itemView.city_humidity

        fun bind(item: CityItem) {
            cityName.text = item.cityName
            cityLat.text = context.getString(R.string.latitude_format).format(item.cityLat)
            cityLng.text = context.getString(R.string.longitude_format).format(item.cityLon)
            cityTemperature.text = context.getString(R.string.temperature_format).format(item.cityTemperature)
            cityHumidity.text = context.getString(R.string.humidity_format).format(item.cityHumidity)

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: CityItem)
    }

    data class CityItem(
        val cityName: String,
        val cityLat: Float,
        val cityLon: Float,
        val cityTemperature: Float,
        val cityHumidity: Int
    )

}