package com.sssofi0101.foodstoreapp.data.retrofit

import com.sssofi0101.foodstoreapp.domain.models.Meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IFoodApi {

    @GET("filter.php?/restaurants/restaurantId/menu?")
    @Headers("Content-type: application/json")
    fun getMenu(@Query("c") c:String): Call<Meals>
}