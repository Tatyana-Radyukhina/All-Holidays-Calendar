package com.example.allholidayscalendar.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allholidayscalendar.CalendarificInterface
import com.example.allholidayscalendar.DTO.HolidaysDTO
import com.example.allholidayscalendar.data.ApiKey
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultFragmentViewModel: ViewModel() {

    val resultFromServer = MutableLiveData<String>()
    val titleFromServer = MutableLiveData<String>()

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
    //Создаем клиент и добавляем туда перехватчик
    val okHttpCLient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://calendarific.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpCLient)
        .build()

    private val service = retrofit.create(CalendarificInterface::class.java)




    fun getInfo(getCountry:String, getYear:Int, getDay:Int, getMonth:Int) {
        service.getHolidays(
            apiKey = ApiKey.api,
            country = getCountry,
            year = getYear,
            day = getDay,
            month = getMonth+1
        ).enqueue(object :
            Callback<HolidaysDTO> {
            @SuppressLint("SuspiciousIndentation", "CommitTransaction")
            override fun onResponse(call: Call<HolidaysDTO>, response: Response<HolidaysDTO>) {
                try {
                    val b = response.body()
                    if (b != null) {
                        resultFromServer.value = b.response.holidays[0].description
                        titleFromServer.value = b.response.holidays[0].name
                    }
                }
                catch (e:Exception){
                    resultFromServer.value = "On this day there are no national holidays in the selected country. Please set other values"
                    titleFromServer.value = null
                }
            }


            override fun onFailure(call: Call<HolidaysDTO>, t: Throwable) {
                resultFromServer.value = "Sorry, please try again"
                titleFromServer.value = null
            }
        })
    }
}



