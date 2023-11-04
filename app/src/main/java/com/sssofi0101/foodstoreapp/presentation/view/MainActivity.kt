package com.sssofi0101.foodstoreapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    private val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        menuViewModel.loadFoodList("Dessert")

        menuViewModel.menuState.observe(this) {
            when (it.status) {
                MenuState.Status.FAILED -> {
                    it.msg?.let { it1 -> Log.d("apiError", it.msg) }
                    Toast.makeText(baseContext, "${it.msg}", Toast.LENGTH_SHORT).show()
                }

                MenuState.Status.LOADING -> Toast.makeText(
                    baseContext,
                    "Загрузка..",
                    Toast.LENGTH_SHORT
                ).show()

                MenuState.Status.SUCCESS -> {
                    Toast.makeText(
                        baseContext,
                        "Успешно",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        menuViewModel.mealsList.observe(this){
            Log.d("MyMeals",it.toString())
        }
    }
}





