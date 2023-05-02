package com.comst19.chapter5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter5.databinding.RvItemBinding

class RVAdapter(private val datas : MutableList<String>) : RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root){

        val item_tv = binding.itemTV

        fun bind(data : String){
            item_tv.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : RvItemBinding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

}