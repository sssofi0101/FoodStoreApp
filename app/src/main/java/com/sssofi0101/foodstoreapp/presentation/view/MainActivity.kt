package com.sssofi0101.foodstoreapp.presentation.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.mainToolbar

        setSupportActionBar(toolbar)
        val rootView = window.decorView as ViewGroup
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.customView = layoutInflater.inflate(R.layout.action_bar, rootView, false)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val lastLocation = sharedPref.getInt("lastLocation", 1)
        val citySpinner = toolbar.findViewById<Spinner>(R.id.citySpinner)
        citySpinner.setSelection(lastLocation)
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sharedPref.edit().putInt("lastLocation",position).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        val navView: BottomNavigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_menu -> {
                    navController.navigate(R.id.menuFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_cart -> {
                    navController.navigate(R.id.cartFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_user -> {
                    navController.navigate(R.id.userFragment)
                    return@setOnItemSelectedListener true
                }

                else -> {true}
            }

        }


    }

}





