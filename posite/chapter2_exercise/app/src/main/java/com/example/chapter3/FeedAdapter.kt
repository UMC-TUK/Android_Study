package com.example.chapter3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class FeedAdapter(private val datas : ArrayList<FeedListModel>, private val context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_list,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = datas.size

    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val instaId : TextView = itemView.findViewById(R.id.insta_id)
        private val profile : ImageView = itemView.findViewById(R.id.profile_img)
        private val images : ViewPager2 = itemView.findViewById(R.id.feed_viewpager)
        private val indicator: CircleIndicator3 = itemView.findViewById(R.id.indicator)
        private val options: ImageButton = itemView.findViewById(R.id.feed_options)
        private val like : ImageButton = itemView.findViewById(R.id.feed_like)
        private val comment: ImageButton = itemView.findViewById(R.id.feed_comment)
        private val sendMessage: ImageButton = itemView.findViewById(R.id.feed_send_message)
        private val mark: ImageButton = itemView.findViewById(R.id.feed_mark)
        private val id: TextView = itemView.findViewById(R.id.writer_id)
        private val title: TextView = itemView.findViewById(R.id.feed_title)
        private val commentField: LinearLayout=itemView.findViewById(R.id.feed_comment_field)
        private val commenterId: TextView = itemView.findViewById(R.id.commenter_id)
        private val commenterContent: TextView = itemView.findViewById(R.id.comment_content)

        fun bind(data1: FeedListModel) {
            instaId.text = data1.id
            profile.setImageResource(data1.profile)
            images.adapter = FeedImgAdapter(data1.images, context)
            id.text = data1.id
            title.text = data1.title
            if(data1.commenterId == null) {
                commentField.visibility = View.GONE
            } else {
                commenterId.text = data1.commenterId
                commenterContent.text = data1.commentContent
                commentField.visibility = View.VISIBLE
            }

            indicator.setViewPager(images)
            indicator.createIndicators(data1.images.size,0)
            options.setOnClickListener {
                val popup = PopupMenu(context, options)
                popup.menuInflater.inflate(R.menu.feed_menu, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.insta_draw -> {
                            Toast.makeText(context, "그리기 눌림!!", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id.insta_message -> {
                            Toast.makeText(context, "보내기 눌림!!", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
            }
            like.setOnClickListener {
                Toast.makeText(context, "좋아요 눌림!!", Toast.LENGTH_SHORT).show()
            }
            comment.setOnClickListener {
                Toast.makeText(context, "댓글 눌림!!", Toast.LENGTH_SHORT).show()
            }
            sendMessage.setOnClickListener {
                Toast.makeText(context, "메시지 보내기 눌림!!", Toast.LENGTH_SHORT).show()
            }
            mark.setOnClickListener {
                Toast.makeText(context, "게시물 저장하기 눌림!!", Toast.LENGTH_SHORT).show()
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