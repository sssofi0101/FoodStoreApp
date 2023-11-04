package com.sssofi0101.foodstoreapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.databinding.ItemBannerBinding

class BannerAdapter(private val bannerList: ArrayList<Int>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    class BannerViewHolder (item: View) : RecyclerView.ViewHolder(item){
        private val binding = ItemBannerBinding.bind(item)
        fun bind(resId:Int)= with(binding){
            imageView.setImageResource(resId)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent,false)

        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun cleanList(){
        bannerList.removeAll(bannerList)
        notifyDataSetChanged()
    }
    fun addDay(image: Int){
        bannerList.add(image)
        notifyItemInserted(itemCount+1)
    }
}