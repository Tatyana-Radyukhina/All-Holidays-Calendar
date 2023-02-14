package com.example.allholidayscalendar

import androidx.recyclerview.widget.RecyclerView
import com.example.allholidayscalendar.databinding.CountryItemBinding

class CountriesViewHolder(binding:CountryItemBinding): RecyclerView.ViewHolder(binding.root) {

    //привязываем view из layout к переменным
    private val name = binding.name
    private val poster = binding.poster

    //Помещаем данные из Countries в View
    fun bind(country:Countries){
        name.text = country.name
        poster.setImageResource(country.poster)
    }
}