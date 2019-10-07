package com.alexyndrik.weatherapp.data.model.api

import com.alexyndrik.weatherapp.data.model.openweathermap.forecast.ForecastResponse
import com.alexyndrik.weatherapp.data.model.openweathermap.weather.WeatherResponse
import com.alexyndrik.weatherapp.openweathermapApiKey
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenweathermapApi {

    @GET("data/2.5/weather")
    fun getWeather(@Query("q") cityCountry: String): Deferred<Response<WeatherResponse>>

    @GET("data/2.5/weather")
    fun getWeather(@Query("lat") lat: Float,
                    @Query("lon") lon: Float): Deferred<Response<WeatherResponse>>

    @GET("data/2.5/forecast")
    fun getForecast(@Query("q") cityCountry: String): Deferred<Response<ForecastResponse>>

    @GET("data/2.5/forecast")
    fun getForecast(@Query("lat") lat: Float,
                     @Query("lon") lon: Float): Deferred<Response<ForecastResponse>>

    companion object Factory {

        private val BASE_URL = "http://api.openweathermap.org/"

        // Creating Auth Interceptor to add api_key query in front of all the requests
        private val authInterceptor = Interceptor {chain ->
            val newUrl = chain.request().url().newBuilder()
                .addQueryParameter("appid", openweathermapApiKey)
                .addQueryParameter("units", "metric")
                .build()
            val newRequest = chain.request().newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }

        // OkHttpClient for building http request url
        private val openweathermapClient = OkHttpClient().newBuilder().addInterceptor(
            authInterceptor
        ).build()

        fun retrofit(): Retrofit = Retrofit.Builder()
            .client(openweathermapClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val openweathermapApi = retrofit()
            .create(OpenweathermapApi::class.java)

    }

}