package com.harnet.countries.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.harnet.countries.viewModel.CountriesViewModel
import com.harnet.countries.R
import com.harnet.countries.adapter.CountriesAdapter
import com.harnet.countries.adapter.SwipeToDelete
import com.harnet.countries.databinding.CountriesFragmentBinding
import com.harnet.countries.model.Country
import com.harnet.countries.model.di.DaggerCountriesApiServiceComponent
import com.harnet.countries.util.setActivityTitle
import kotlinx.android.synthetic.main.countries_fragment.*
import javax.inject.Inject

class CountriesFragment : Fragment() {
    @Inject
    lateinit var countriesAdapter: CountriesAdapter

    lateinit var dataBinding: CountriesFragmentBinding

    private lateinit var viewModel: CountriesViewModel

    init {
        // inject adapter with empty list as an argument
        DaggerCountriesApiServiceComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.countries_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        countriesAdapter = CountriesAdapter(arrayListOf())

        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        setActivityTitle("Countries list")

        viewModel.refresh()

        countries_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter

            //Swipe to delete
            val itemTouchHelper = ItemTouchHelper(SwipeToDelete(countriesAdapter))
            itemTouchHelper.attachToRecyclerView(countries_recyclerView)
        }

        // add separation line between items
        countries_recyclerView.addItemDecoration(
            DividerItemDecoration(
                countries_recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        observeModel()

    }

    private fun observeModel() {
        viewModel.getCountries().observe(viewLifecycleOwner, Observer { countriesList ->

            countriesList?.let {
                if (countriesList.isNotEmpty()) {
                    loading_progressBar.visibility = View.INVISIBLE
                    countries_recyclerView.visibility = View.VISIBLE
                    countriesAdapter.updateCountriesList(countriesList as ArrayList<Country>)
                }
            }
        })

        viewModel.getIsLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                if (isLoading) {
                    countries_recyclerView.visibility = View.INVISIBLE
                    loading_progressBar.visibility = View.VISIBLE
                } else {
                    countries_recyclerView.visibility = View.VISIBLE
                    loading_progressBar.visibility = View.INVISIBLE
                }
            }

        })

        viewModel.getErrorLoading().observe(viewLifecycleOwner, Observer { isError ->
            if (isError) {
                error_message.visibility = View.VISIBLE
                loading_progressBar.visibility = View.INVISIBLE
                countries_recyclerView.visibility = View.INVISIBLE
            }
        })
    }

}