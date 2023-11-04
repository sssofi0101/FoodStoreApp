package com.sssofi0101.foodstoreapp.domain.interfaces

import com.sssofi0101.foodstoreapp.domain.models.Meals
import retrofit2.Call

interface FoodDataSource {
    fun getMeals(category:String) : Call<Meals>
}