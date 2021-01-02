package com.harnet.countries.model.di

import com.harnet.countries.model.CountriesApiService
import com.harnet.countries.viewModel.CountriesViewModel
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface CountriesApiServiceComponent {
    fun getCountriesApiService(): CountriesApiService

    fun inject(countriesViewModel: CountriesViewModel)
}