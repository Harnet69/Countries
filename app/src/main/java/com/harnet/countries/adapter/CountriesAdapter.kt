package com.harnet.countries.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.harnet.countries.R
import com.harnet.countries.databinding.ItemCountryBinding
import com.harnet.countries.model.Country
import com.harnet.countries.util.getProgressDrawable
import com.harnet.countries.util.loadImage

class CountriesAdapter(private var countriesList: ArrayList<Country>): RecyclerView.Adapter<CountriesAdapter.ImagesViewHolder>() {

    fun updateCountriesList(newCountriesList: ArrayList<Country>){
        if(newCountriesList.isNotEmpty()){
            countriesList.clear()
            countriesList.addAll(newCountriesList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.ImagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // elements of the list transforms into views. DataBinding approach

        val view = DataBindingUtil.inflate<ItemCountryBinding>(
            inflater,
            R.layout.item_country,
            parent,
            false
        )
        return ImagesViewHolder(view)
    }

    class ImagesViewHolder(var view: ItemCountryBinding): RecyclerView.ViewHolder(view.root)

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.view.countryName.text = countriesList[position].name
        holder.view.countryCapital.text = countriesList[position].capital
        holder.view.countryFlagUrl.text = countriesList[position].flag
        holder.view.countryFlag.loadImage(countriesList[position].flag, getProgressDrawable(holder.view.countryFlag.context))
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }
}