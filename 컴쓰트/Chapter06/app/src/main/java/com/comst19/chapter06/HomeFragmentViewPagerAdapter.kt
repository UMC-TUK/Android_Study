package com.comst19.chapter06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter06.databinding.HomePagerBinding

class HomeFragmentViewPagerAdapter(val homes : MutableList<Int>) : RecyclerView.Adapter<HomeFragmentViewPagerAdapter.PagerViewHolder>(){

    inner class PagerViewHolder(private val binding : HomePagerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(home : Int){
            binding.homeImg.setImageResource(home)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(HomePagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(homes[position])
    }

    override fun getItemCount(): Int {
        return homes.size
    }
}