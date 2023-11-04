package com.sssofi0101.foodstoreapp.presentation.view

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
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val bannerRc = view.findViewById<RecyclerView>(R.id.banner_rc)
//        val categoryRc = view.findViewById<RecyclerView>(R.id.category_rc)
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

        menuViewModel.loadFoodList("Dessert")
        binding.apply {
            dessertChip.setOnClickListener {
                menuViewModel.loadFoodList("Dessert")
            }
            starterChip.setOnClickListener {
                menuViewModel.loadFoodList("Starter")
            }
            pastaChip.setOnClickListener {
                menuViewModel.loadFoodList("Pasta")
            }
            seafoodChip.setOnClickListener {
                menuViewModel.loadFoodList("Seafood")
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
    }

}