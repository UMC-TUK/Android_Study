package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SwitchListBinding

class SwitchAdapter (private val context: Context, private val stats: ArrayList<SwitchModel>) : RecyclerView.Adapter<SwitchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SwitchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = stats.size



    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(private val binding: SwitchListBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int ,stats:ArrayList<SwitchModel>) {
            stats[position].position = position
            binding.textPosiiton.text = position.toString()
            binding.textStatus.text = stats[position].status.toString()
            binding.switchStatus.isChecked = stats[position].status
            binding.switchStatus.setOnCheckedChangeListener { buttonView, isChecked ->
                stats[position].status = isChecked
                binding.textStatus.text = isChecked.toString()
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, stats)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}