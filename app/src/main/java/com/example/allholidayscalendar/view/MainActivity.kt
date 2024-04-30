package com.example.allholidayscalendar.view

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Build
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.allholidayscalendar.R
import com.example.allholidayscalendar.databinding.ActivityMainBinding
import com.example.allholidayscalendar.viewModels.ResultFragmentViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var selection: String
    lateinit var valDay:Any
    lateinit var valMonth:Any
    lateinit var valYear:Any

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultFragmentViewModel: ResultFragmentViewModel by viewModels()

        ObjectAnimator.ofFloat(binding.sceneRoot, View.SCALE_X, 1F, 0F).apply {
            startDelay = 5000
            start()
        }

        binding.datePicker.setOnClickListener {
            DatePickerDialog(this@MainActivity, { view, year, month, dayOfMonth ->
                valDay = dayOfMonth
                valMonth = month
                valYear = year},
                currentYear, currentMonth, currentDay).show()
        }


        val suggestions =listOf("Afghanistan/af", "Albania/al", "Algeria/dz", "Andorra/ad",
            "Angola/ao", "Antigua and Barbuda/ag", "Argentina/ar",
            "USA","Australia","England", "New Zealand", "Serbia", "Serbia/rs")

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
                selection =
                    cursor.getString(cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1))
                binding.CustomSearchView.setQuery(selection, false)
                selection.split("/")

                return true
            }

            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }
        })


        binding.sendInTofrag.setOnClickListener {
            resultFragmentViewModel.getInfo()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_placeholder, ResultFragment())
                .addToBackStack(null)
                .commit()

        }
}



    private val calendar = Calendar.getInstance()
    private val currentYear = calendar.get(Calendar.YEAR)
    private val currentMonth = calendar.get(Calendar.MONTH)
    private val currentDay = calendar.get(Calendar.DAY_OF_MONTH)}


































