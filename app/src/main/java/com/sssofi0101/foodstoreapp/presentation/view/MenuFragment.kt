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
import androidx.room.Room
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.data.room.AppDatabase
import com.sssofi0101.foodstoreapp.data.room.DatabseImpl
import com.sssofi0101.foodstoreapp.databinding.FragmentMenuBinding
import com.sssofi0101.foodstoreapp.domain.models.Meal
import com.sssofi0101.foodstoreapp.presentation.adapters.BannerAdapter
import com.sssofi0101.foodstoreapp.presentation.adapters.MealAdapter
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuState
import com.sssofi0101.foodstoreapp.presentation.viewmodel.MenuViewModel


class MenuFragment : Fragment() {
    private val menuViewModel by viewModels<MenuViewModel>()
    //private val menuViewModel = this.activity?.let { MenuViewModel(it.application) }
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
        Log.d("activity?.application","${activity?.application}")
        val database:AppDatabase  = Room.databaseBuilder(activity?.application?.applicationContext!!, AppDatabase::class.java, "mydatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        val databaseiImpl = DatabseImpl(database)


        val context = this.requireContext()
        if (hasConnection(context)){
            menuViewModel.loadFoodList("Starter",databaseiImpl)
        }
        else {
            menuViewModel.loadCachedFoodList("Starter",databaseiImpl)
        }
        binding.apply {
            dessertChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Dessert",databaseiImpl)
                }
                else {
                    menuViewModel.loadCachedFoodList("Dessert",databaseiImpl)
                }
            }
            starterChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Starter",databaseiImpl)
                }
                else {
                    menuViewModel.loadCachedFoodList("Starter",databaseiImpl)
                }
            }
            pastaChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Pasta",databaseiImpl)
                }
                else {
                    menuViewModel.loadCachedFoodList("Pasta",databaseiImpl)
                }
            }
            seafoodChip.setOnClickListener {
                if (hasConnection(context)){
                    menuViewModel.loadFoodList("Seafood",databaseiImpl)
                }
                else {
                    menuViewModel.loadCachedFoodList("Seafood",databaseiImpl)
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
        return if (wifiInfo != null && wifiInfo.isConnected) {
            true
        } else false
    }

}