package com.sssofi0101.foodstoreapp.domain.usecases

import com.sssofi0101.foodstoreapp.domain.interfaces.FoodDataSource
import com.sssofi0101.foodstoreapp.domain.models.Meals

class GetMenuUseCase (private val foodDataSource: FoodDataSource) {
    operator fun invoke(category:String):Meals{
        return foodDataSource.getMeals(category)
    }
}