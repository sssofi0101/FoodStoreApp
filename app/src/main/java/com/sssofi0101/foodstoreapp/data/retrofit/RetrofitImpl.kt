package com.sssofi0101.foodstoreapp.data.retrofit

import android.util.Log
import android.widget.Toast
import com.sssofi0101.foodstoreapp.domain.interfaces.FoodDataSource
import com.sssofi0101.foodstoreapp.domain.models.Meals
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : FoodDataSource {

    private fun getService() : IFoodApi
    {
        return createRetrofit(LoggingInterceptor.interceptor).create(IFoodApi::class.java)
    }

    private fun createRetrofit(interceptor: HttpLoggingInterceptor) :  Retrofit{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://themealdb.com/api/json/v1/1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    override fun getMeals(category: String) : Call<Meals> {
        return getService().getMenu(category)

    }

}