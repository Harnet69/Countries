package com.harnet.countries.model.di

import com.harnet.countries.adapter.CountriesAdapter
import com.harnet.countries.model.CountriesApiService
import com.harnet.countries.view.CountriesFragment
import com.harnet.countries.viewModel.CountriesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CountriesAdapterModule::class])
interface CountriesApiServiceComponent {

    fun inject(countriesViewModel: CountriesViewModel)

    fun inject(countriesFragment: CountriesFragment)
}