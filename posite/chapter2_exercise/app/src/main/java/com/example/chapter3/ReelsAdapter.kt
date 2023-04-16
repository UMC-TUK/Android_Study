package com.example.chapter3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ReelsAdapter (private val datas : ArrayList<ReelsModel>, private val context: Context) : RecyclerView.Adapter<ReelsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reels_list,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = datas.size

    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val instaId : TextView = itemView.findViewById(R.id.insta_id)
        private val profile : ImageView = itemView.findViewById(R.id.profile_img)
        private val parent: ConstraintLayout = itemView.findViewById(R.id.reels_parent)

        fun bind(data1: ReelsModel) {
            instaId.text = data1.id
            profile.setImageResource(data1.img)
            parent.setOnClickListener {
                Toast.makeText(context, "${instaId.text}의 릴스 눌림!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}