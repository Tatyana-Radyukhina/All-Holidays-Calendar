package com.example.allholidayscalendar

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.allholidayscalendar.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //создаю календарь

        binding.datePicker.setOnClickListener {
            DatePickerDialog(this, { view, year, month, dayOfMonth ->
                val date = "$year $month $dayOfMonth"
                binding.dateText.text = date
            },
                currentYear, currentMonth, currentDay).show()
        }

    }

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
}
