package com.example.chapter3

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThumbnailAdapter(private val datas: ArrayList<ContentModel>, private val context: Context) : RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size



    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val profile: ImageView = itemView.findViewById(R.id.content_thumbnail)
        private val type: ImageView = itemView.findViewById(R.id.content_type)

        fun bind(data1: ContentModel) {
            profile.setImageResource(data1.thumbnail)
            if (data1.type == "reels") {
                type.setImageResource(R.drawable.baseline_smart_display_24)
                val layoutParams = FrameLayout.LayoutParams(profile.layoutParams.width, profile.layoutParams.height*2)
                profile.layoutParams = layoutParams
            } else {
                type.setImageResource(R.drawable.baseline_photo_library_24)
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