package com.example.allholidayscalendar.view

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.allholidayscalendar.CalendarificInterface
import com.example.allholidayscalendar.HolidaysDTO
import com.example.allholidayscalendar.R
import com.example.allholidayscalendar.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //в этом методе происходит инициализация активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service.getHolidays("8f630c135b1ca1993ce7dd7ce65975f0b9d23966","rs", 2019).enqueue(object : Callback<HolidaysDTO> {
            override fun onResponse(call: Call<HolidaysDTO>, response: Response<HolidaysDTO>) {
                println("!!!" + response.body())
            }

            override fun onFailure(call: Call<HolidaysDTO>, t: Throwable) {
                println("ошибка")
                t.printStackTrace()
            }
        })



        ObjectAnimator.ofFloat(binding.sceneRoot, View.SCALE_X, 1F, 0F).apply {
            startDelay = 5000
            start()
        }

        val suggestions =listOf("USA","Australia","England", "New Zealand")

        val searchView = binding.CustomSearchView
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.searchItemID)

        val cursorAdapter = androidx.cursoradapter.widget.SimpleCursorAdapter(
            applicationContext,
            R.layout.suggestion_item_layout,
            null,
            from,
            to,
            androidx.cursoradapter.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        binding.CustomSearchView.suggestionsAdapter = cursorAdapter

        binding.CustomSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                newText?.let {

                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(newText, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        binding.CustomSearchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = binding.CustomSearchView.suggestionsAdapter.getItem(position) as Cursor
                val selection =
                    cursor.getString(cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1))
                binding.CustomSearchView.setQuery(selection, false)

                // Do whatever you want with selection text

                return true
            }

            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }
        })




        //Getting instance of search Widgets
//        val searchViewWidget:SearchView = findViewById<SearchView>(R.id.CustomSearchView)
//        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
//        searchViewWidget.setSearchableInfo(searchManager.getSearchableInfo(componentName))



        //создаю календарь

        binding.datePicker.setOnClickListener {
            DatePickerDialog(this, { view, year, month, dayOfMonth ->
                val date = "$year $month $dayOfMonth"
                binding.dateText.text = date


//                    val transaction = supportFragmentManager.beginTransaction()
//                    val frag2 = ChooseFragment()
//                    frag2.arguments = bundleOf("token" to date)
//
//                    transaction.replace(R.id.master_layout, frag2)
//                    transaction.addToBackStack(null)
//                    transaction.commit()

            },
                currentYear, currentMonth, currentDay).show()
        }

    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://calendarific.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    interface CalendarificInterface {
//        @GET("/holidays")
//        fun getHolidays(
//            @Query("api_key") apiKey: String,
//            @Query("country") country: String,
//            @Query("year") year: Int,
//
//        ): Call<HolidaysDTO>
//    }


    val service = retrofit.create(CalendarificInterface::class.java)





//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.search, menu)
//
//        // Get the SearchView and set the searchable configuration
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
//            // Assumes current activity is the searchable activity
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
//        }
//
//        return true
//    }




    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
}














































//    private val suggestions = arrayOf(
//        "item1", "item2",
//        "item3", "item4",
//        "item5", "item6",
//        "item7", "item8"
//    )

//    private lateinit var mAdapter: SimpleCursorAdapter










//        val menuItem = menu?.findItem(R.id.search)
//
//        val searchView = menuItem?.actionView as SearchView

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null) {
//                    populateAdapter(newText)
//                }
//                return false
//            }
//        })
//    }

//        searchView.suggestionsAdapter = mAdapter
//
//        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
//            override fun onSuggestionSelect(position: Int): Boolean {
//                return true
//            }
//
//            override fun onSuggestionClick(position: Int): Boolean {
//                val cursor: Cursor = mAdapter!!.getItem(position) as Cursor
//                val txt: String = cursor.getString(cursor.getColumnIndexOrThrow("items"))
//                searchView.setQuery(txt, true)
//                searchView.clearFocus()
//                return true
//            }
//        })
//
//
//        return super.onCreateOptionsMenu(menu)
//    }


//    fun populateAdapter(query: String) {
//        val c = MatrixCursor(arrayOf(BaseColumns._ID, "items"))
//        for (i in suggestions.indices) {
//            if (suggestions[i].lowercase()
//                    .startsWith(query.lowercase())
//            ) c.addRow(arrayOf(i, suggestions[i]))
//        }
//        mAdapter!!.changeCursor(c)
//    }









//        val from = arrayOf("items")
//        val to = intArrayOf(android.R.id.text2)
//
//        mAdapter = SimpleCursorAdapter(
//            this,
//            R.layout.item_search,
//            null,
//            from,
//            to,
//            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
//        )










