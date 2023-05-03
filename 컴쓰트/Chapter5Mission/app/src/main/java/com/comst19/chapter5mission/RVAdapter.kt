package com.comst19.chapter5mission

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter5mission.databinding.RecyclerItemBinding

class RVAdapter(val memos : MutableList<String>,val itemClickListener: itemClickListener) : RecyclerView.Adapter<RVAdapter.MyViewHolder>()  {

    inner class MyViewHolder(private val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){

        //val root = binding.root
        fun bind(memo : String){
            // 리스트 뷰 데이터를 UI 연동
            binding.memoTV.text = memo
            binding.menu.setOnClickListener {
                itemClickListener.onClick(adapterPosition, it)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    // 뷰홀더 생성, 각 리스트 아이템 1개씩 구성될 때마다 함수 호출 됨
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : RecyclerItemBinding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰홀더가 바인딩 (결합) 이루어질 떄 해줘야 될 처리들을 구현
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(memos[position])
//        holder.root.setOnClickListener {
//            Toast.makeText(holder.root.context, "누름", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int {
        return memos.size
    }
}