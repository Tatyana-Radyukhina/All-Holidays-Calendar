package com.example.allholidayscalendar.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allholidayscalendar.Countries
import com.example.allholidayscalendar.view.rv_viewholders.CountriesViewHolder
import com.example.allholidayscalendar.databinding.CountryItemBinding


//в параметр передаём слушатель, чтобы мы потом могли обрабатывать нажатие
class RecyclerCountriesAdapter(var data:List<Countries>, private val clickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //тут храним список элементов для ресайклер вью
    val itemsCountries = mutableListOf<Countries>()


    //привязываем наш вью холдер и передаём туда надутую вёртску элемента со страной
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(binding)
    }


    //тут происходит привязка полей из объекта Countries к нашему layout country_item
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is CountriesViewHolder ->{
                //Вызываем метод bind(), который мы создали, и передаем туда объект
                //из нашей базы данных с указанием позиции
                holder.bind(itemsCountries[position])
            }
        }
    }

    //переопределяем метод - возвращаем количество элементов в списке RV
    override fun getItemCount() = itemsCountries.size


    //метод для добавления объектов в список
    fun addItems(list: List<Countries>){
        itemsCountries.clear()
        itemsCountries.addAll(list)
//Уведомляем RV, что пришел новый список, и ему нужно заново все "привязывать"
        notifyItemRangeChanged(0, itemsCountries.size)
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(country: Countries)
    }

}