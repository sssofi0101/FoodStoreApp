package com.sssofi0101.foodstoreapp.domain.usecases

import com.sssofi0101.foodstoreapp.domain.interfaces.FoodDataSource

class getMenuUseCase (private val foodDataSource: FoodDataSource) {
    operator fun invoke(category:String){
        foodDataSource.getMeals(category)
    }
}