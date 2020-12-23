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
import com.harnet.countries.util.setActivityTitle

class CountriesFragment : Fragment() {

    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        setActivityTitle("Countries list")

        viewModel.refresh()

        observeModel()

    }

    private fun observeModel(){
        viewModel.getCountries().observe(viewLifecycleOwner, Observer { countriesList ->
            if(countriesList.isNotEmpty()){
                //TODO refresh RecyclerView with new countryList, show countries recyclerView block
                Log.i("countriesFromApi", "onViewCreated: $countriesList")

            }

        })

        viewModel.getIsLoading().observe(viewLifecycleOwner, Observer {isLoading ->
            if(isLoading){
                //TODO hide countries recyclerView block, show progressbar

            }else{
                //TODO hide progress bar
            }
        })

        viewModel.getErrorLoading().observe(viewLifecycleOwner, Observer {isError ->
            if(isError){
                //TODO show error message, hide progress bar and countries RecyclerViewBlock
            }
        })
    }

}