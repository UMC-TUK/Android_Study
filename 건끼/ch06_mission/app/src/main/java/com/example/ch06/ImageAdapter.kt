package com.example.ch06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch06.databinding.ItemViewBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    var data = listOf<Int>()

    inner class ViewHolder(val binding : ItemViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: Int){
            binding.image.setImageResource(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount()= data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}