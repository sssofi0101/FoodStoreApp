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
        withContext(Dispatchers.IO) {
            try {
                _menuState.postValue(MenuState.LOADING)
                val meals = getMenuUseCase.invoke(category)
                _menuState.postValue(MenuState.LOADED)
                _mealsList.postValue(meals)

            } catch (e: Exception) {
                _menuState.postValue(MenuState.error(e.message))
            }
        }
    }
    }

}