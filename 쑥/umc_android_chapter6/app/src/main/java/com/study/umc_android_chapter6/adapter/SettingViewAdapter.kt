package com.study.umc_android_chapter6.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.data.Setting
import com.study.umc_android_chapter6.databinding.SettingItemBinding


class SettingViewAdapter (private val setting : ArrayList<Setting>) :
    RecyclerView.Adapter<SettingViewAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_item, parent, false)
        return viewHolder(SettingItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemSetting.text= setting[position].setting
    }

    override fun getItemCount(): Int {
        return setting.size
    }

    inner class viewHolder(val binding: SettingItemBinding) : RecyclerView.ViewHolder(binding.root)
}