package com.sssofi0101.foodstoreapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CategoryMeal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryMealDao(): CategoryMealDao
}