package com.harnet.countries.model

import retrofit2.http.GET

interface CountriesApi {
    // whole URL is https://raw.githubusercontent.com/DevTides/countries/master/countriesV2.json
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): retrofit2.Response<List<Country>>
}