package com.study.umc_chapter8.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_chapter8.R
import com.study.umc_chapter8.entity.Dictionary
import com.study.umc_chapter8.databinding.ItemBinding


class DictionaryAdapter(private var dictionares : MutableList<Dictionary>) :
    RecyclerView.Adapter<DictionaryAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return viewHolder(ItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemEn.text=dictionares[position].english
        holder.binding.itemKr.text = dictionares[position].korean
    }

    override fun getItemCount(): Int {
        return dictionares.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class viewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}