package com.harnet.countries.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesApiService {
    // base URL of the API
    private val BASE_URL = "https://raw.githubusercontent.com"

    // object created by Retrofit for accessing to an endpoint
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        // handle all basic communication, separate threads, errors and converts JSON to object of our class
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountriesApi::class.java)// create model class

    //get observable List from API
    fun getCountriesApi(): CountriesApi {
        return api
    }
}