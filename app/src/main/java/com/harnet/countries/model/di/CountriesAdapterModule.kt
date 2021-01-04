package com.harnet.countries.model.di

import com.harnet.countries.adapter.CountriesAdapter
import dagger.Module
import dagger.Provides

@Module
class CountriesAdapterModule {
    // provide a list on the static way
    @Module
    companion object{
        // instantiate it here
        @JvmStatic
        @Provides // tels Dagger this should be part of a graph
        fun provideCountriesAdapter(): CountriesAdapter = CountriesAdapter(arrayListOf())
    }
}