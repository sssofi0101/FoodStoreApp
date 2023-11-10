package com.sssofi0101.foodstoreapp.presentation.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.databinding.FragmentMenuBinding
import com.sssofi0101.foodstoreapp.domain.models.Meal
import com.sssofi0101.foodstoreapp.presentation.adapters.BannerAdapter
import com.sssofi0101.foodstoreapp.presentation.adapters.MealAdapter
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuState
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuViewModel


class MenuFragment : Fragment() {
    private val menuViewModel by viewModels<MenuViewModel>()
    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding)
        {
            bannerRc.layoutManager =
                LinearLayoutManager(this@MenuFragment.requireContext(), LinearLayoutManager.HORIZONTAL, false)
            bannerRc.adapter = BannerAdapter(arrayListOf(R.drawable.banner1, R.drawable.banner2))

            val mealsRc = view.findViewById<RecyclerView>(R.id.meal_rc)
            mealsRc.layoutManager =
                LinearLayoutManager(this@MenuFragment.requireContext(), LinearLayoutManager.VERTICAL, false)
            mealsRc.addItemDecoration(
                DividerItemDecoration(
                    this@MenuFragment.requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )

        }
        val mealsAdapter = MealAdapter(arrayListOf())
        binding.mealRc.adapter = mealsAdapter

        val context = this.requireContext()
        if (hasConnection(context)){
            menuViewModel.loadFoodList("Starter")
        }
        else {
            menuViewModel.loadCachedFoodList("Starter")
        }
        binding.apply {
            dessertChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Dessert")
                }
                else {
                    menuViewModel.loadCachedFoodList("Dessert")
                }
            }
            starterChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Starter")
                }
                else {
                    menuViewModel.loadCachedFoodList("Starter")
                }
            }
            pastaChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Pasta")
                }
                else {
                    menuViewModel.loadCachedFoodList("Pasta")
                }
            }
            seafoodChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Seafood")
                }
                else {
                    menuViewModel.loadCachedFoodList("Seafood")
                }
            }
        }


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

        menuViewModel.cacheMealsList.observe(viewLifecycleOwner){
            mealsAdapter.cleanList()
            for (meal in it){
                mealsAdapter.addMeal(Meal(meal.name,meal.imageUri,meal.id))
            }
        }
    }
    fun hasConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }

}