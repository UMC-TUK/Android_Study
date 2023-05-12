package com.study.umc_android_chapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.databinding.ItemBinding
import com.study.umc_android_chapter6.data.Post

class PostViewAdapter(private val post : ArrayList<Post>) :
    RecyclerView.Adapter<PostViewAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return viewHolder(ItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemTitle.text=post[position].title
        holder.binding.itemContext.text = post[position].context
        holder.binding.itemImage.setImageResource(post[position].image)
    }

    override fun getItemCount(): Int {
            return post.size
    }

    inner class viewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

}