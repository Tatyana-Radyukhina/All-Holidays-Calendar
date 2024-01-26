package com.example.allholidayscalendar

data class Holiday(
    val date: Date,
    val description: String,
    val name: String,
    val type: List<String>
)