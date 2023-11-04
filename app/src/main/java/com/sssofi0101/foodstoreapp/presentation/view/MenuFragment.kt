package com.sssofi0101.foodstoreapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.domain.models.Meal
import com.sssofi0101.foodstoreapp.presentation.adapters.BannerAdapter
import com.sssofi0101.foodstoreapp.presentation.adapters.CategoryAdapter
import com.sssofi0101.foodstoreapp.presentation.adapters.MealAdapter
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuState
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuViewModel

class MenuFragment : Fragment() {
    private val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bannerRc = view.findViewById<RecyclerView>(R.id.banner_rc)
        val categoryRc = view.findViewById<RecyclerView>(R.id.category_rc)
        bannerRc.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.HORIZONTAL,false)
        bannerRc.adapter = BannerAdapter(arrayListOf(R.drawable.banner1,R.drawable.banner2))
        categoryRc.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryRc.adapter = CategoryAdapter(arrayListOf("Стартеры","Паста", "Морепродукты","Десерты"))
        val mealsRc = view.findViewById<RecyclerView>(R.id.meal_rc)
        mealsRc.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL,false)
        mealsRc.addItemDecoration(DividerItemDecoration(this.requireContext(),LinearLayoutManager.VERTICAL))
        val mealsAdapter = MealAdapter(arrayListOf())
        mealsRc.adapter = mealsAdapter


        menuViewModel.loadFoodList("Dessert")

        menuViewModel.menuState.observe(viewLifecycleOwner) {
            when (it.status) {
                MenuState.Status.FAILED -> {
                    it.msg?.let { it1 -> Log.d("apiError", it.msg) }
                    Toast.makeText(this.requireContext(), "${it.msg}", Toast.LENGTH_SHORT).show()
                }

                MenuState.Status.LOADING -> Toast.makeText(
                    this.requireContext(),
                    "Загрузка..",
                    Toast.LENGTH_SHORT
                ).show()

                MenuState.Status.SUCCESS -> {
                    Toast.makeText(
                        this.requireContext(),
                        "Успешно",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        menuViewModel.mealsList.observe(viewLifecycleOwner){
            Log.d("MyMeals",it.toString())
            mealsAdapter.cleanList()
            for (meal in it.meals){
                mealsAdapter.addMeal(meal)
            }
        }
    }

}