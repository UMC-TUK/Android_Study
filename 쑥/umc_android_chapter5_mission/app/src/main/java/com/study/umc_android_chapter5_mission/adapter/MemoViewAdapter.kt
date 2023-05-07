package com.study.umc_android_chapter5_mission.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.umc_android_chapter5_mission.R
import com.study.umc_android_chapter5_mission.data.Memo
import com.study.umc_android_chapter5_mission.databinding.ItemBinding
import java.util.*

class MemoViewAdapter(private val Memo : LinkedList<Memo>) :
    RecyclerView.Adapter<MemoViewAdapter.viewHolder>() {
    private lateinit var itemClickListener : OnItemClickListener
    private lateinit var itemLongClickListener : OnItemLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return viewHolder(ItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemTitle.text=Memo[position].title
        holder.binding.itemContext.text = Memo[position].context
        holder.binding.itemSwitch.isChecked = Memo[position].checked
        setColor(holder)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onLongClick(it, position)
        }
        holder.binding.itemSwitch.setOnClickListener {
            Memo[position].checked = holder.binding.itemSwitch.isChecked
            setColor(holder)
        }
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    interface OnItemLongClickListener {
        fun onLongClick(v: View, position: Int) : Boolean
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setItemLongClickListener(itemLongClickListener: OnItemLongClickListener){
        this.itemLongClickListener = itemLongClickListener
    }

    fun setColor(holder: viewHolder){
        if(holder.binding.itemSwitch.isChecked){
            holder.binding.item.setBackgroundResource(R.color.green_black)
            holder.binding.itemTitle.setTextColor(Color.WHITE)
            holder.binding.itemContext.setTextColor(Color.WHITE)
        }else{
            holder.binding.item.setBackgroundResource(R.color.white)
            holder.binding.itemTitle.setTextColor(Color.parseColor("#FF0A1A1A"))
            holder.binding.itemContext.setTextColor(Color.parseColor("#FF0A1A1A"))
        }
    }

    override fun getItemCount(): Int {
        return Memo.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class viewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}
