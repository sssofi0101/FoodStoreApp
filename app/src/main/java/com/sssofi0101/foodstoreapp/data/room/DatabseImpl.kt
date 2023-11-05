package com.sssofi0101.foodstoreapp.data.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.sssofi0101.foodstoreapp.domain.interfaces.FoodCacheDataSource
import com.sssofi0101.foodstoreapp.domain.models.Meal

class DatabseImpl(private val database: AppDatabase) : FoodCacheDataSource {

    //private val database = provideDatabase()

//    fun provideDatabase():AppDatabase{
//        return Room.databaseBuilder(application, AppDatabase::class.java, "mydatabase")
//            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()
//    }

    override fun getMeals(category: String): List<CategoryMeal> {
            return database.categoryMealDao().findByCategory(category)
    }

    override fun addCategoryMeals(meals: List<CategoryMeal>, category: String) {
        database.categoryMealDao().deleteAllCategory(category)
        database.categoryMealDao().insertAll(*meals.toTypedArray())
       // database.categoryMealDao().insertAll(*meals.map { it -> CategoryMeal(it.idMeal, it.strMeal,it.strMealThumb,category) }.toTypedArray())
    }



}