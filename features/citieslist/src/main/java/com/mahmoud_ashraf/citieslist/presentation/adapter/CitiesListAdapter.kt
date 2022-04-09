package com.mahmoud_ashraf.citieslist.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud_ashraf.citieslist.R
import com.mahmoud_ashraf.citieslist.databinding.ItemCityBinding
import com.mahmoud_ashraf.core.entity.CitiesResponse

class CitiesListAdapter : RecyclerView.Adapter<CityViewHolder>() {

     val data: MutableList<CitiesResponse.Item> = mutableListOf()


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
       holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CityViewHolder =
       parent.context
                .let { ItemCityBinding.inflate(LayoutInflater.from(it), parent, false) }
                .let(::CityViewHolder)

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<CitiesResponse.Item>) {
        this.data.clear()
        this.data.addAll(list)
        notifyDataSetChanged()
    }
}


class CityViewHolder(
   private val binding: ItemCityBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(city: CitiesResponse.Item) {
        with(binding) {
            tvCountryName.text = city.country.name
          tvCityName.text = city.name
            tvCityLatitude.text = itemView.context.getString(R.string.lat).plus(city.lat?.toString()?:"")
            tvCityLongitude.text =itemView.context.getString(R.string.lng).plus(city.lng?.toString()?:"")
        }


    }


}