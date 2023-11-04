package com.sssofi0101.foodstoreapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sssofi0101.foodstoreapp.R
import com.sssofi0101.foodstoreapp.presentation.adapters.BannerAdapter

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
        bannerRc.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.HORIZONTAL,false)
        bannerRc.adapter = BannerAdapter(arrayListOf(R.drawable.banner1,R.drawable.banner2))
        bannerRc.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

}