package com.sssofi0101.foodstoreapp.domain.interfaces

import androidx.lifecycle.LiveData
import com.sssofi0101.foodstoreapp.data.room.CategoryMeal
import com.sssofi0101.foodstoreapp.domain.models.Meal

interface FoodCacheDataSource {
    fun getMeals(category: String) : List<CategoryMeal>


    fun addCategoryMeals(meals:List<CategoryMeal>, category:String)
}