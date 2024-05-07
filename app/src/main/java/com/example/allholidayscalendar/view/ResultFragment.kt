package com.example.allholidayscalendar.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.allholidayscalendar.databinding.FragmentResultBinding
import com.example.allholidayscalendar.viewModels.ResultFragmentViewModel
import kotlin.math.hypot


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding:FragmentResultBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultFragmentViewModel: ResultFragmentViewModel by activityViewModels()



        resultFragmentViewModel.resultFromServer.observe(viewLifecycleOwner){
            binding.description.text = it
        }
        resultFragmentViewModel.titleFromServer.observe(viewLifecycleOwner){
            binding.title.text = it
        }
    }




    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}








