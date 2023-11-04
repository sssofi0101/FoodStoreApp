package com.sssofi0101.foodstoreapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.databinding.ItemMealBinding
import com.sssofi0101.foodstoreapp.domain.models.Meal
import kotlin.random.Random

class MealAdapter (private val mealList:ArrayList<Meal>) : RecyclerView.Adapter<MealAdapter.MealViewHolder>(){
    class MealViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemMealBinding.bind(item)

        fun bind(meal: Meal) = with(binding){
            Picasso.get().load("${meal.strMealThumb}").into(mealImageview)
            mealNameTv.text = meal.strMeal
            mealDescrTv.text = "Попробуйте наше новое вкусное блюдо ${meal.strMeal}"
            priceButton.text = "от ${Random.nextInt(0, 2000)} р"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal, parent,false)

        return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
       holder.bind(mealList[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun cleanList(){
        mealList.removeAll(mealList)
        notifyDataSetChanged()
    }
    fun addMeal(meal: Meal){
        mealList.add(meal)
        notifyItemInserted(itemCount+1)
    }
}