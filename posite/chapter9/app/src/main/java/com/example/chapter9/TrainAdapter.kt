package com.example.chapter9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter9.databinding.TrainLilstBinding

class TrainAdapter (private val datas : List<Data>) : RecyclerView.Adapter<TrainAdapter.ViewHolder>() {
    private lateinit var binding: TrainLilstBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = TrainLilstBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }
    override fun getItemCount(): Int = datas.size

    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data1: Data) {
            binding.currentLoc.text = data1.loc_cur_nm
            binding.destLoc.text = data1.dest_st_nm
            binding.currentState.text = data1.train_state
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

}