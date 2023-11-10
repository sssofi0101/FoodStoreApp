package com.sssofi0101.foodstoreapp.data.room

import com.sssofi0101.foodstoreapp.domain.interfaces.FoodCacheDataSource

class DatabseImpl(private val database: AppDatabase) : FoodCacheDataSource {


    override fun getMeals(category: String): List<CategoryMeal> {
            return database.categoryMealDao().findByCategory(category)
    }

    override fun addCategoryMeals(meals: List<CategoryMeal>, category: String) {
        database.categoryMealDao().deleteAllCategory(category)
        database.categoryMealDao().insertAll(*meals.toTypedArray())
    }



}