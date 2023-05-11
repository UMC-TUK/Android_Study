package com.example.chapter6_exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter6_exercise.databinding.ImgListBinding

class ImgAdapter (private val imgList: ArrayList<Int>) : RecyclerView.Adapter<ImgAdapter.ViewHolder>() {
    lateinit var binding:ImgListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ImgListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = imgList.size

    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(private val binding: ImgListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data1: Int) {
            binding.imgItem.setImageResource(data1)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imgList[position % imgList.size])
    }

}