package com.study.umc_chapter9.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_chapter9.R
import com.study.umc_chapter9.databinding.CommentBinding
import com.study.umc_chapter9.retrofit.data.DataModel

class Adapter (private var comments : List<DataModel>) : RecyclerView.Adapter<Adapter.viewHolder>(){


    inner class viewHolder(val binding: CommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return viewHolder(CommentBinding.bind(view))
    }

    override fun getItemCount(): Int = comments.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.name.text = (position+1).toString()+ "\t"+ comments[position].name
        holder.binding.body.text = comments[position].body
    }

}