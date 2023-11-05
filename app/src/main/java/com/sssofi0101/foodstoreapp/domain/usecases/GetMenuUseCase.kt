package com.sssofi0101.foodstoreapp.domain.usecases

import com.sssofi0101.foodstoreapp.domain.interfaces.FoodDataSource
import com.sssofi0101.foodstoreapp.domain.models.Meals
import retrofit2.Call

class GetMenuUseCase (private val foodDataSource: FoodDataSource) {
    operator fun invoke(category:String): Call<Meals> {
        return foodDataSource.getMeals(category)
    }
}