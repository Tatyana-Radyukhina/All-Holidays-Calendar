package com.example.allholidayscalendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allholidayscalendar.databinding.FragmentChooseBinding


class ChooseFragment : Fragment() {

    lateinit var countriesAdapter: RecyclerCountriesAdapter
    private var _binding: FragmentChooseBinding? = null
    private val binding: FragmentChooseBinding
        get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

    }

    fun initRecycler(){
        binding.recyclerCountries.apply {
            countriesAdapter = RecyclerCountriesAdapter(databaseCountries, object:RecyclerCountriesAdapter.OnItemClickListener{
                override fun click(country: Countries) {
                    TODO("Not yet implemented")
                }
            })
            //присваиваем адаптер
            adapter = countriesAdapter
            //Присваиваем layoutManager
            layoutManager = GridLayoutManager(requireContext(),3)
        }

        countriesAdapter.addItems(databaseCountries)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}