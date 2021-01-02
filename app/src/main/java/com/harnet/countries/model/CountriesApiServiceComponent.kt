package com.harnet.countries.model

import com.harnet.countries.viewModel.CountriesViewModel
import dagger.Component

@Component
interface CountriesApiServiceComponent {
    fun getCountriesApiService(): CountriesApiService

    fun inject(countriesViewModel: CountriesViewModel)
}