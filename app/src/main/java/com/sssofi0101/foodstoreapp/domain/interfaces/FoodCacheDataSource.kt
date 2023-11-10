package com.sssofi0101.foodstoreapp.domain.interfaces

import com.sssofi0101.foodstoreapp.data.room.CategoryMeal

interface FoodCacheDataSource {
    fun getMeals(category: String) : List<CategoryMeal>


    fun addCategoryMeals(meals:List<CategoryMeal>, category:String)
}