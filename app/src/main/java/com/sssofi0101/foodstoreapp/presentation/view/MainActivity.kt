package com.sssofi0101.foodstoreapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.databinding.ActivityMainBinding
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuState
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.mainToolbar

        setSupportActionBar(binding.mainToolbar)
        val rootView = window.decorView as ViewGroup
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.customView = layoutInflater.inflate(R.layout.action_bar, rootView, false)

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





