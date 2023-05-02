package com.example.ch05

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch05.databinding.ItemViewBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var dataList: List<String> = listOf()
    inner class ViewHolder(private val viewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: String) {
            viewBinding.textView.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

}