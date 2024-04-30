package com.example.allholidayscalendar.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allholidayscalendar.CalendarificInterface
import com.example.allholidayscalendar.DTO.HolidaysDTO
import com.example.allholidayscalendar.data.ApiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultFragmentViewModel: ViewModel() {

    val resultFromServer = MutableLiveData<String>()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://calendarific.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CalendarificInterface::class.java)

    fun getInfo() {
        service.getHolidays(
            apiKey = ApiKey.api,
            "rs",
            2019,
            5,
            6
        ).enqueue(object :
            Callback<HolidaysDTO> {
            @SuppressLint("SuspiciousIndentation", "CommitTransaction")
            override fun onResponse(call: Call<HolidaysDTO>, response: Response<HolidaysDTO>) {
                val b = response.body()
                if (b != null) {
                            resultFromServer.value = b.response.holidays[0].description
                }
            }

            override fun onFailure(call: Call<HolidaysDTO>, t: Throwable) {
                resultFromServer.value = "Sorry, please try again"
            }
        })
    }
}



