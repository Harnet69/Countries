package com.harnet.countries.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.countries.model.Country
import kotlinx.coroutines.*

class CountriesViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val mCountries = MutableLiveData<List<Country>>()
    private val mIsLoading = MutableLiveData<Boolean>()
    private val mErrorLoading = MutableLiveData<Boolean>()

    fun getCountries() = mCountries
    fun getIsLoading() = mIsLoading
    fun getErrorLoading() = mErrorLoading

    fun refresh(){
        getCountryFromAPI()
    }

    private fun getCountryFromAPI(){
        mIsLoading.value = true

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
            coroutineScope.launch {
                coroutineScope.async {
                    delay(3000)
                    mCountries.value = countries
                    mIsLoading.value = false
                }
            }
        }

        //TODO mIsLoading.value = false and mErrorLoading = true if Retrofit can't get a data
    }
}