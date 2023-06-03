package com.study.umc_chapter9_mission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_chapter9_mission.R
import com.study.umc_chapter9_mission.data.RecyclerItem
import com.study.umc_chapter9_mission.databinding.ItemBinding

class RecyclerAdapter(private val items : MutableList<RecyclerItem>)
    :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){


    inner class ViewHolder(val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(ItemBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            itemName.text = items[position].name
            itemLocal.text = items[position].local
            itemEffect.text = items[position].effect
        }
    }

    override fun getItemViewType(position: Int): Int = position
}