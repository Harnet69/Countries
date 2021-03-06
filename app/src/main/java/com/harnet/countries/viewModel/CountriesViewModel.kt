package com.harnet.countries.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.countries.model.CountriesApiService
import com.harnet.countries.model.Country
import com.harnet.countries.model.di.DaggerCountriesApiServiceComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class CountriesViewModel : ViewModel() {
    // instance which works through Dagger's dependency injections
    @Inject
    lateinit var countriesApiService: CountriesApiService

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception ${throwable.localizedMessage}")
    }

    private val mCountries = MutableLiveData<List<Country>>()
    private val mIsLoading = MutableLiveData<Boolean>()
    private val mErrorLoading = MutableLiveData<Boolean>()

    fun getCountries() = mCountries
    fun getIsLoading() = mIsLoading
    fun getErrorLoading() = mErrorLoading

    init {
        // call Dagger generated class and inject there all dependencies
        DaggerCountriesApiServiceComponent.create().inject(this)
    }

    fun refresh() {
        getCountryFromAPI()
    }

    private fun getCountryFromAPI() {
        mIsLoading.value = true

        // variable job is used for cancelling if viewModel is dismissed
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            delay(1000L) // for simulating data downloading delaying
            val response = countriesApiService.getCountriesApi().getCountries()
            // switch for updating the UI thread
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { retrieveCountries(it) }
                } else {
                    onError("Exception ${response.message()}")
                }
            }
        }
    }

    private fun onError(s: String) {
        mErrorLoading.value = true
        mIsLoading.value = false
    }

    private fun retrieveCountries(countriesList: List<Country>) {
        mCountries.value = countriesList
        mIsLoading.value = false
        mErrorLoading.value = false
    }

    // cancel a job when the viewModel will be terminated
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}