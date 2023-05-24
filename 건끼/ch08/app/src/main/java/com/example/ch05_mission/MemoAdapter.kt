package com.example.ch05_mission

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch05_mission.databinding.ItemMemoBinding
import com.example.ch05_mission.entity.MemoEntity
class MemoAdapter : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    private var dataList: List<MemoEntity> = listOf()
    lateinit var clickListener: (MemoEntity) -> Unit
    lateinit var db: AppDatabase

    inner class ViewHolder(private val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MemoEntity, ) {

            binding.detailT.text = data.detail
            binding.titleT.text = data.title
            binding.boxBtn.isChecked = data.liked

            binding.root.setOnClickListener {
                clickListener(data)
            }
             binding.boxBtn.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    data.liked=true
                    Thread(Runnable {
                        db.memoDao().insertMemo(data)
                    }).start()

                }else{
                    data.liked=false
                    Thread(Runnable{
                        db.memoDao().deleteMemo(data.title)
                    }).start()
                }
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
