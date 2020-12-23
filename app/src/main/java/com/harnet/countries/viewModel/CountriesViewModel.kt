package com.harnet.countries.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.countries.model.CountriesApiService
import com.harnet.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class CountriesViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val countriesApiService = CountriesApiService()
    private val disposable = CompositeDisposable()

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

//        val countries = mutableListOf<Country>()
//    // mock countries getting
//        //TODO implement Retrofit functionality here
//        countries.add(Country("Russia", "Moscow", ""))
//        countries.add(Country("Poland", "Warsaw", ""))
//        countries.add(Country("Belarus", "Minsk", ""))
//        countries.add(Country("England", "London", ""))
//        countries.add(Country("USA", "Washington", ""))
//        countries.add(Country("Lithuania", "Vilnius", ""))
//
//        if(countries.isNotEmpty()){
//            coroutineScope.launch {
//                coroutineScope.async {
//                    delay(3000)
//                    mCountries.value = countries
//                    mIsLoading.value = false
//                }
//            }
//        }

        //TODO mIsLoading.value = false and mErrorLoading = true if Retrofit can't get a data


        disposable.add(
            // set it to a different thread(passing this call to the background thread)
            countriesApiService.getCountries()
                .subscribeOn(Schedulers.newThread())
                // retrieve it from a background to the main thread for displaying
                .observeOn(AndroidSchedulers.mainThread())
                // pass our Single object here, it's created and implemented
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    // get list of DogBreed objects
                    override fun onSuccess(countriesList: List<Country>) {
                        retrieveCountries(countriesList)
                    }

                    // get an error
                    override fun onError(e: Throwable) {
                        mErrorLoading.value = true
                        mIsLoading.value = false
                        // print stack of error to handling it
                        e.printStackTrace()
                        Log.i("RetrofitError", e.localizedMessage)
                    }
                })
        )
    }

    private fun retrieveCountries(countriesList: List<Country>){
        mCountries.value = countriesList
        mIsLoading.value = false
        mErrorLoading.value = false
    }
}