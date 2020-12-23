package com.harnet.countries.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.harnet.countries.viewModel.CountriesViewModel
import com.harnet.countries.R

class CountriesFragment : Fragment() {

    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        viewModel.refresh()

        observeModel()

    }

    private fun observeModel(){

        viewModel.getMCountries().observe(viewLifecycleOwner, Observer {
            Log.i("countriesFromApi", "onViewCreated: $it")
        })

    }

}