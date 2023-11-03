package com.sssofi0101.foodstoreapp.domain.models

import com.google.gson.annotations.SerializedName

data class MealDetailed(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strArea")
    val strArea: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strInstructions")
    val strInstructions: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)