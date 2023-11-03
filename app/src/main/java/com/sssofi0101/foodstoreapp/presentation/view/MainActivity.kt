package com.sssofi0101.foodstoreapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuState
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuViewModel

class MainActivity : AppCompatActivity() {

    private val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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





