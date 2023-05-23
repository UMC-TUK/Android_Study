package com.example.chapter8_2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter8_2.databinding.MusicListBinding

class MusicListAdapter (private val datas: List<MusicEntity>, private val context: Context)
    : RecyclerView.Adapter<MusicListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("memo1", datas.toString())
        val binding = MusicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = datas.size

    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(private val binding: MusicListBinding)  : RecyclerView.ViewHolder(binding.root){
        fun bind(data1: MusicEntity) {
            binding.musicName.text = data1.name
            binding.musicArtist.text = data1.artist

        }

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }


}