package com.comst19.chapter08mission

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter08mission.databinding.DialogAddBinding
import com.comst19.chapter08mission.databinding.ListItemMemoBinding
import com.comst19.chapter08mission.databinding.MenuDialogBinding
import com.comst19.chapter08mission.db.AppDatabase
import com.comst19.chapter08mission.db.MemoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LikeRVAdapter(private var memoList : ArrayList<MemoEntity>) : RecyclerView.Adapter<LikeRVAdapter.MyViewHolder>() {

    private lateinit var db : AppDatabase
    private lateinit var menuDialog : AlertDialog

    inner class MyViewHolder(private val binding : ListItemMemoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(memo : MemoEntity){
            binding.tvContent.text = memo.memoContent
            binding.tvDate.text = memo.memoDate
            binding.like.isChecked = memo.good

            binding.like.setOnClickListener {
                memo.good = binding.like.isChecked
                val newLike = binding.like.isChecked
                CoroutineScope(Dispatchers.IO).launch {
                    val innerListMemo = db.getMemoDao().getAllMemo()
                    for(m in innerListMemo){
                        if(m.memoContent == memo.memoContent && m.memoDate == memo.memoDate){
                            m.good = newLike
                            db.getMemoDao().updateMemo(m)
                        }
                    }
                    memoList.removeAt(adapterPosition)
                    (binding.root.context as Activity).runOnUiThread {
                        notifyDataSetChanged()
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        db = AppDatabase.getInstance(binding.root.context)!!

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(memoList[position])
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

}