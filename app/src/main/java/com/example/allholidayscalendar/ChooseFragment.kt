package com.example.allholidayscalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.allholidayscalendar.databinding.FragmentChooseBinding


class ChooseFragment : Fragment() {

//    lateinit var holidaysAdapter: RecyclerCountriesAdapter
    private var _binding: FragmentChooseBinding? = null
    private val binding: FragmentChooseBinding
        get() = _binding!!

    val databaseCountries = listOf<Countries>(
        Countries(R.drawable.usa),
                Countries(R.drawable.brasil),
                Countries(R.drawable.denmark),
                Countries(R.drawable.canada),
                Countries(R.drawable.ukraine),
                Countries(R.drawable.portugal),
                Countries(R.drawable.china),
                Countries(R.drawable.romania),
                Countries(R.drawable.argentina)

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}