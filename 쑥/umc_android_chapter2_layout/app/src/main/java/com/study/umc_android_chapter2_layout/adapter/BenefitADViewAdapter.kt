package com.study.umc_android_chapter2_layout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter2_layout.R
import com.study.umc_android_chapter2_layout.data.BenefitAD

class BenefitADViewAdapter (private val BenefitAD : ArrayList<BenefitAD>) :
    RecyclerView.Adapter<BenefitADViewAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.benefit_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = BenefitAD[position].title
            holder.context.text = BenefitAD[position].content
            holder.image.setImageResource(BenefitAD[position].image)
        }

        override fun getItemCount(): Int {
            return BenefitAD.size
        }

        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
            val title: TextView =itemView.findViewById(R.id.title_benefit)
            val context: TextView =itemView.findViewById(R.id.content_benefit)
            val image: ImageView =itemView.findViewById(R.id.image_benefit)
        }
}