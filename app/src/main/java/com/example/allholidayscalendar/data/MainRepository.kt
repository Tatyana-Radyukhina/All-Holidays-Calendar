package com.example.allholidayscalendar.data

import com.example.allholidayscalendar.Countries
import com.example.allholidayscalendar.R

class MainRepository {

    val databaseCountries = listOf<Countries>(
        Countries("USA", R.drawable.usa),
        Countries("Brazil", R.drawable.brasil),
        Countries("Denmark", R.drawable.denmark),
        Countries("Canada", R.drawable.canada),
        Countries("Ukraine", R.drawable.ukraine),
        Countries("Portugal", R.drawable.portugal),
        Countries("China", R.drawable.china),
        Countries("Romania", R.drawable.romania),
        Countries("Argentina", R.drawable.argentina)

    )
}