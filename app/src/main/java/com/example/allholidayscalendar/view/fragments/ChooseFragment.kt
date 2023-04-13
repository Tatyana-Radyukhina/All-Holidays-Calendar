package com.example.allholidayscalendar.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allholidayscalendar.Countries
import com.example.allholidayscalendar.R
import com.example.allholidayscalendar.view.rv_adapters.RecyclerCountriesAdapter
import com.example.allholidayscalendar.databinding.FragmentChooseBinding
import com.example.allholidayscalendar.view_model.ChooseFragmentViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ChooseFragment : Fragment() {

    lateinit var countriesAdapter: RecyclerCountriesAdapter
    private var _binding: FragmentChooseBinding? = null
    private val binding: FragmentChooseBinding
        get() = _binding!!

    //лениво инициализируем ViewModel
    private val viewModel by lazy{
        ViewModelProvider.NewInstanceFactory().create(ChooseFragmentViewModel::class.java)
    }

    //создаём переменную куда будем класть БД из viewModel, используем backing field
    private var countriesDataBase = listOf<Countries>()
        set(value){
            //Если придёт такое же значение, выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            countriesAdapter.addItems(field)
        }

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

        viewModel.countriesListLiveData.observe(viewLifecycleOwner, Observer<List<Countries>> {
            countriesDataBase = it
        })

    }

    fun initRecycler(){
        binding.recyclerCountries.apply {
            countriesAdapter = RecyclerCountriesAdapter(countriesDataBase, object:
                RecyclerCountriesAdapter.OnItemClickListener {
                override fun click(country: Countries) {
                    TODO("Not yet implemented")
                }
            })
            //присваиваем адаптер
            adapter = countriesAdapter
            //Присваиваем layoutManager
            layoutManager = GridLayoutManager(requireContext(),3)
        }

        countriesAdapter.addItems(countriesDataBase)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}


