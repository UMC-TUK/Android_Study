package com.example.chapter5_exercise


import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter5_exercise.databinding.MemoListBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class MemoListAdapter(private val datas: ArrayList<MemoModel>, private val context: Context, private val bins: ArrayList<MemoModel>)
    : RecyclerView.Adapter<MemoListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("memo1", datas.toString())
        val binding = MemoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = datas.size


    fun addItem(item: MemoModel) {
        datas.add(0, item) // 새 아이템을 리스트의 맨 앞에 추가
        notifyItemInserted(0) // RecyclerView에 아이템 추가 알림
    }
    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(private val binding: MemoListBinding)  : RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {
        init {
            binding.memoContent.setOnCreateContextMenuListener(this)
        }
        private var current = 0
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data1: MemoModel, currentPosition: Int) {
            current = currentPosition
            val dateSplit = data1.date.split(":")
            data1.title?.let {
                if(it.isBlank()){
                    binding.memoTitle.text = "메모 ${dateSplit[1]}${dateSplit[2]}"
                } else {
                    binding.memoTitle.text = it
                }
            }
            data1.content?.let {
                binding.memoContent.text = it
            }
            val current = LocalDateTime.now()
            val formatter1 = DateTimeFormatter.ofPattern("yyyy:MM:dd")
            val formatDate = current.format(formatter1)
            val formatSplit = formatDate.split(":")
            if(formatSplit[0] == dateSplit[0] && formatSplit[1] == dateSplit[1] && formatSplit[2] == dateSplit[2]) {
                binding.memoTime.text = data1.time
            } else {
                binding.memoTime.text = "${dateSplit[1]}월${dateSplit[2]}일"
            }
            binding.memoContent.setOnClickListener {
                Intent(context, MemoActivity::class.java).apply{
                    putExtra("memo", data1)
                    flags = FLAG_ACTIVITY_NEW_TASK
                }.run{context.startActivity(this)}
            }



        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val inflater = MenuInflater(v?.context)
            // 컨텍스트 메뉴 레이아웃을 인플레이트
            inflater.inflate(R.menu.context, menu)
            menu?.findItem(R.id.action_bin)?.setOnMenuItemClickListener {
                deleteItem(current)
                notifyDataSetChanged()
                true
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], position)
    }


    fun deleteItem(position: Int) {
        bins.add(datas[position])
        datas.removeAt(position)
        Log.d("memo1", "bins: $bins")
        notifyItemRemoved(position)
    }

}