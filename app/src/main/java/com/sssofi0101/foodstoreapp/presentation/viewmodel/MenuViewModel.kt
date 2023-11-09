package com.sssofi0101.foodstoreapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sssofi0101.foodstoreapp.data.retrofit.RetrofitImpl
import com.sssofi0101.foodstoreapp.data.room.CategoryMeal
import com.sssofi0101.foodstoreapp.data.room.DatabseImpl
import com.sssofi0101.foodstoreapp.domain.models.Meals
import com.sssofi0101.foodstoreapp.domain.usecases.CachedMenuUseCase
import com.sssofi0101.foodstoreapp.domain.usecases.GetMenuUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel() : ViewModel() {


    private val _menuState = MutableLiveData<MenuState>()
    val menuState: LiveData<MenuState>
        get() = _menuState

    private val _mealsList = MutableLiveData<Meals>()
    val mealsList: LiveData<Meals>
        get() = _mealsList

    private val _cacheMealsList = MutableLiveData<List<CategoryMeal>>()
    val cacheMealsList: LiveData<List<CategoryMeal>>
        get() = _cacheMealsList

    val retrofitImpl = RetrofitImpl()
    private val getMenuUseCase = GetMenuUseCase(retrofitImpl)

    private lateinit var cachedMenuUseCase :CachedMenuUseCase

    fun loadCachedFoodList(category: String, databseImpl: DatabseImpl) {
        cachedMenuUseCase = CachedMenuUseCase(databseImpl)
        viewModelScope.launch {
            try {
                _cacheMealsList.postValue(cachedMenuUseCase.load(category))

            }
            catch (e: Exception) {
                _menuState.postValue(MenuState.error("Произошла ошибка при получении данных"))
            }
        }
    }

    fun loadFoodList(category:String, databseImpl: DatabseImpl)  {
        cachedMenuUseCase = CachedMenuUseCase(databseImpl)

        viewModelScope.launch(Dispatchers.Main) {

            try {
                _menuState.postValue(MenuState.LOADING)
                val response = getMenuUseCase.invoke(category)
                response.enqueue(object : Callback<Meals> {

                    override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                        if (response.body() != null) {
                            _mealsList.postValue(response.body())
                            val categoryMeals = response.body()!!.meals.map { it -> CategoryMeal(it.idMeal,it.strMeal,it.strMealThumb,category) }
                            //_cacheMealsList.postValue(categoryMeals)
                            cachedMenuUseCase.save(categoryMeals,category)

                            _menuState.postValue(MenuState.LOADED)
                        }
                        else {
                            _menuState.postValue(MenuState.error("Произошла ошибка при получении данных"))
//                            _mealsList.postValue(Meals(
//                                loadCachedMenuUseCase.invoke(category).map { it -> it.map { it -> Meal(it.name, it.imageUri, it.id) } }
//                            ))
                        }
                    }

                    override fun onFailure(call: Call<Meals>, t: Throwable) {
                        _menuState.postValue(MenuState.error("Произошла ошибка ${t.localizedMessage}"))
                    }

                })

            } catch (e: Exception) {
                _menuState.postValue(MenuState.error(e.message))
            }

    }
    }

}