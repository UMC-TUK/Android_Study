package com.study.umc_android_chapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.data.Ad
import com.study.umc_android_chapter6.databinding.AdViewpagerBinding

class AdViewAdapter (private val ad: ArrayList<Ad>) : RecyclerView.Adapter<AdViewAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ad_viewpager, parent, false)
        return PagerViewHolder(AdViewpagerBinding.bind(view))
    }
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.binding.itemTitle.text = ad[position % ad.size].title
        holder.binding.layout.setBackgroundResource(ad[position % ad.size].background)
        holder.binding.itemImage.setImageResource(ad[position % ad.size].image)
    }

    override fun getItemCount(): Int = ad.size

    inner class PagerViewHolder(val binding: AdViewpagerBinding) : RecyclerView.ViewHolder(binding.root)
}