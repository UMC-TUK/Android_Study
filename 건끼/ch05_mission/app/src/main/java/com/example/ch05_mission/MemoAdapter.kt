package com.example.ch05_mission

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ch05_mission.databinding.ItemMemoBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    private var dataList: List<MemoEntity> = listOf()
    lateinit var clickListener: (MemoEntity) -> Unit

    inner class ViewHolder(private val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MemoEntity, ) {

            binding.detailT.text = data.detail
            binding.titleT.text = data.title

            binding.root.setOnClickListener {
                clickListener(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            =ViewHolder(ItemMemoBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setDataList(data: List<MemoEntity>) {
        this.dataList = data
        notifyDataSetChanged()
    }
}
