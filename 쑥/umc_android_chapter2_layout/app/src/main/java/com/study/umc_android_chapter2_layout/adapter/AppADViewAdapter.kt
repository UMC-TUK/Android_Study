package com.study.umc_android_chapter2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter2_layout.data.AppAD
import com.study.umc_android_chapter2_layout.R

class AppADViewAdapter(private val AppAD : ArrayList<AppAD>) :
    RecyclerView.Adapter<AppADViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = AppAD[position].title
        holder.context.text = AppAD[position].context
        holder.image.setImageResource(AppAD[position].image)
    }

    override fun getItemCount(): Int {
            return AppAD.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val title: TextView =itemView.findViewById(R.id.item_title)
        val context: TextView =itemView.findViewById(R.id.item_context)
        val image: ImageView =itemView.findViewById(R.id.item_image)
    }


}