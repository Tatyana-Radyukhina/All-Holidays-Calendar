package com.example.allholidayscalendar.DTO

import com.example.allholidayscalendar.DTO.Date

data class Holiday(
    val date: Date,
    val description: String,
    val name: String,
    val type: List<String>
)