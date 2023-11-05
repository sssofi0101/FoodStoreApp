package com.sssofi0101.foodstoreapp.domain.usecases

import com.sssofi0101.foodstoreapp.data.room.CategoryMeal
import com.sssofi0101.foodstoreapp.domain.interfaces.FoodCacheDataSource
import com.sssofi0101.foodstoreapp.domain.models.Meal

class CachedMenuUseCase(private val foodCacheDataSource: FoodCacheDataSource) {

    fun load(category:String): List<CategoryMeal> {
        return foodCacheDataSource.getMeals(category)
    }

    fun save(meals:List<CategoryMeal>, category: String){
        foodCacheDataSource.addCategoryMeals(meals,category)
    }
}