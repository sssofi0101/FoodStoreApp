package com.sssofi0101.foodstoreapp.domain.interfaces

import com.sssofi0101.foodstoreapp.domain.models.Meals

interface FoodDataSource {
    fun getMeals(category:String) : Meals?
}