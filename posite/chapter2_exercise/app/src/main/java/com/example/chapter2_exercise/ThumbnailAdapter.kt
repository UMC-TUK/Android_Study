package com.example.chapter2_exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
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
                type.setImageResource(R.drawable.video)
                if(data1.columnSapn ==2) {
                    val layoutParams = FrameLayout.LayoutParams(profile.layoutParams.width, profile.layoutParams.height*2)
                    profile.layoutParams = layoutParams
                }
            } else {
                type.setImageResource(R.drawable.page)
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