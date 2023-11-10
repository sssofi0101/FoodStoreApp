package com.sssofi0101.foodstoreapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryMealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg meals: CategoryMeal)

    @Query("DELETE FROM menu WHERE category LIKE :category")
    fun deleteAllCategory(category: String)

    @Query("SELECT * FROM menu WHERE category LIKE :category")
    fun findByCategory(category: String): List<CategoryMeal>
}