package com.example.allholidayscalendar.view

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.allholidayscalendar.R
import com.example.allholidayscalendar.databinding.ActivityMainBinding
import com.example.allholidayscalendar.view.fragments.ChooseFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //в этом методе происходит инициализация активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ObjectAnimator.ofFloat(binding.sceneRoot, View.SCALE_X, 1F, 0F).apply {
            startDelay = 5000
            start()
        }


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, ChooseFragment())
            .addToBackStack(null)
            .commit()



        //создаю календарь

        binding.datePicker.setOnClickListener {
            DatePickerDialog(this, { view, year, month, dayOfMonth ->
                val date = "$year $month $dayOfMonth"
                binding.dateText.text = date


                    val transaction = supportFragmentManager.beginTransaction()
                    val frag2 = ChooseFragment()
                    frag2.arguments = bundleOf("token" to date)

                    transaction.replace(R.id.master_layout, frag2)
                    transaction.addToBackStack(null)
                    transaction.commit()

            },
                currentYear, currentMonth, currentDay).show()
        }

    }

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
}



//        val scene0 = Scene.getSceneForLayout(binding.sceneroot, R.layout.activity_main, this)
//        val transitionManager = TransitionManager()
//        transitionManager.setTransition(scene0, Explode().apply{
//            duration = 1500
//        })
//        transitionManager.transitionTo(scene0)

//        TransitionManager.beginDelayedTransition(binding.maincontainer, Fade())
//        binding.maincontainer.children.forEach { it.translationX += 10 }



