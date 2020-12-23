package com.harnet.countries.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.countries.model.Country

class CountriesViewModel : ViewModel() {

    private val mCountries = MutableLiveData<List<Country>>()

    fun getMCountries() = mCountries

    fun refresh(){
        getCountryFromAPI()
    }

    private fun getCountryFromAPI(){
        val countries = mutableListOf<Country>()
    // mock countries getting
        //TODO implement Retrofit functionality here
        countries.add(Country("Russia", "Moscow", ""))
        countries.add(Country("Poland", "Warsaw", ""))
        countries.add(Country("Belarus", "Minsk", ""))
        countries.add(Country("England", "London", ""))
        countries.add(Country("USA", "Washington", ""))
        countries.add(Country("Lithuania", "Vilnius", ""))

        if(countries.isNotEmpty()){
            mCountries.value = countries
        }
    }
}