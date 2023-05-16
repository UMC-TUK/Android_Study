package com.study.umc_android_chapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.databinding.ItemImageBinding

class ImageViewAdapter (private val image : ArrayList<Int>) :
    RecyclerView.Adapter<ImageViewAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return viewHolder(ItemImageBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemImage.setImageResource(image[position])
    }

    override fun getItemCount(): Int {
        return image.size
    }

    inner class viewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}