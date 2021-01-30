package com.harnet.countries.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.harnet.countries.R
import com.harnet.countries.databinding.ItemCountryBinding
import com.harnet.countries.model.Country

class CountriesAdapter(private var countriesList: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ImagesViewHolder>() {

    fun updateCountriesList(newCountriesList: ArrayList<Country>) {
        for (country: Country in newCountriesList) {
            Log.i("updateCountriesListQrr", country.name.toString())
        }
        if (newCountriesList.isNotEmpty()) {
            countriesList.clear()
            countriesList.addAll(newCountriesList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesAdapter.ImagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<ItemCountryBinding>(
            inflater,
            R.layout.item_country,
            parent,
            false
        )
        return ImagesViewHolder(view)
    }

    // Swipe to delete
    fun deleteItem(pos: Int, context: Context) {
        Toast.makeText(context, "${countriesList[pos].name} was deleted", Toast.LENGTH_SHORT).show()
        countriesList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    class ImagesViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root)

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.view.country = countriesList[position]
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }
}