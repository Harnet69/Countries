package com.harnet.countries.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
    // whole URL is https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json

    // annotation used for knowing how this method can be used
    @GET("rest/v2/all")// !!!this is an end point, not all url
    fun getCountries(): Single<List<Country>>

    // it can be several methods for different kinds of a data
}