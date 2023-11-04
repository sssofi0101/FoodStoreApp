package com.sssofi0101.foodstoreapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sssofi0101.foodstoreapp.data.retrofit.RetrofitImpl
import com.sssofi0101.foodstoreapp.domain.models.Meals
import com.sssofi0101.foodstoreapp.domain.usecases.GetMenuUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    val retrofitImpl = RetrofitImpl()
    private val getMenuUseCase = GetMenuUseCase(retrofitImpl)

    fun loadFoodList(category:String)  {

        viewModelScope.launch(Dispatchers.Main) {

            try {
                _menuState.postValue(MenuState.LOADING)
                val response = getMenuUseCase.invoke(category)
                response.enqueue(object : Callback<Meals> {

                    override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                        if (response.body() != null) {
                            _mealsList.postValue(response.body())
                            _menuState.postValue(MenuState.LOADED)
                        }
                        else {
                            _menuState.postValue(MenuState.error("Произошла ошибка при получении данных"))
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