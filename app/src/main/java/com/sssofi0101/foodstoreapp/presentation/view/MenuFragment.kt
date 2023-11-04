package com.sssofi0101.foodstoreapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.domain.models.Meal
import com.sssofi0101.foodstoreapp.presentation.adapters.BannerAdapter
import com.sssofi0101.foodstoreapp.presentation.adapters.CategoryAdapter
import com.sssofi0101.foodstoreapp.presentation.adapters.MealAdapter

class MenuFragment : Fragment() {

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
        mealsRc.adapter = MealAdapter(arrayListOf(Meal("паста 1","https://www.themealdb.com/images/media/meals/usywpp1511189717.jpg","1"),
            Meal("паста 2","https://www.themealdb.com/images/media/meals/0jv5gx1661040802.jpg","2")))

    }

}