package com.app.covid19tracker.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.app.covid19tracker.R
import com.app.covid19tracker.ui.daily.DailyDataFragment
import com.app.covid19tracker.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val homeFragment: HomeFragment by inject()
    private val dailyDataFragment: DailyDataFragment by inject()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment_container)
        fab_global.setOnClickListener(this)
        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.homeFragment -> {
                    fab_global.show()
                }
                R.id.dailyDataFragment -> {
                    fab_global.hide()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_global -> {
                navController?.navigate(R.id.action_homeFragment_to_dailyDataFragment)
            }
        }
    }
}