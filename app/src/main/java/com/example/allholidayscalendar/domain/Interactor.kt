package com.example.allholidayscalendar.domain

import com.example.allholidayscalendar.Countries
import com.example.allholidayscalendar.data.MainRepository

//нужен для того, чтобы забирать нашу базу из слоя model
class Interactor(val repo: MainRepository) {
    fun getCountries(): List<Countries> = repo.databaseCountries

}