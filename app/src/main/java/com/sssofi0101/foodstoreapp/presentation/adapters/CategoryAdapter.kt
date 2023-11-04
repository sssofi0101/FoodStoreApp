package com.sssofi0101.foodstoreapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.databinding.ItemBannerBinding
import com.sssofi0101.foodstoreapp.databinding.ItemCategoryBinding
import java.util.ArrayList

class CategoryAdapter(private val categoryList: ArrayList<String>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> (){
    class CategoryViewHolder(item: View) : RecyclerView.ViewHolder(item){
        private val binding = ItemCategoryBinding.bind(item)
        fun bind(categoryName:String)= with(binding){
            textView.text = categoryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent,false)

        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }
}