package com.study.umc_android_chapter5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter5.R
import com.study.umc_android_chapter5.data.Memo
import com.study.umc_android_chapter5.databinding.ItemBinding

class MemoViewAdapter(private val Memo : ArrayList<Memo>) :
    RecyclerView.Adapter<MemoViewAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return viewHolder(ItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemTitle.text=Memo[position].title
        holder.binding.itemContext.text = Memo[position].context
        holder.binding.itemImage.setImageResource(Memo[position].image)
    }

    override fun getItemCount(): Int {
            return Memo.size
    }

    inner class viewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

}