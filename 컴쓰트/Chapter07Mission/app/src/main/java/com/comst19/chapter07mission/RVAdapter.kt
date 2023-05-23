package com.comst19.chapter07mission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter07mission.databinding.MusicItemBinding

class RVAdapter(val musics : MutableList<Int>, private val listener : OnItemClickListener) : RecyclerView.Adapter<RVAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding : MusicItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Int){
            binding.musicName.text = "Music $item"
            binding.root.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}