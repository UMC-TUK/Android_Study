package com.example.chapter8_exercise

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter8_exercise.databinding.MemoListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class MemoListAdapter( private val context: Context, private val datas: MutableList<MemoModel>)
    : RecyclerView.Adapter<MemoListAdapter.ViewHolder>(){

    private var memoDB: MemoDB? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("memo1", datas.toString())
        val binding = MemoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = datas.size

    //리사이클러 뷰의 요소들을 넣어줌
    inner class ViewHolder(private val binding: MemoListBinding)  : RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {
        init {
            binding.memoContent.setOnCreateContextMenuListener(this)
        }
        private var current = 0
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data1: MemoModel, currentPosition: Int) {
            current = currentPosition
            binding.memoLike.isChecked = data1.like
            val sharedPreference = context.getSharedPreferences("sp1", MODE_PRIVATE)
            if(sharedPreference.getBoolean(data1.id.toString(), false)) {
                binding.memoFavorite.setBackgroundResource(R.drawable.star)
            } else {
                binding.memoFavorite.setBackgroundResource(R.drawable.favorite)
            }
            // data 저장!
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
            binding.memoFavorite.setOnClickListener {
                if(binding.memoFavorite.tag == "false") {
                    val shared = context.getSharedPreferences("sp1", MODE_PRIVATE)
                    val edit  : SharedPreferences.Editor = shared.edit()
                    binding.memoFavorite.setBackgroundResource(R.drawable.star)
                    binding.memoFavorite.tag = "true"
                    edit.putBoolean(data1.id.toString(), true)
                    edit.apply()
                } else {
                    if(context is FavoriteStorageActivity) {
                        Log.d("favorite", "favorite에서 삭제")
                        deleteItem(currentPosition)
                    }
                    val shared = context.getSharedPreferences("sp1", MODE_PRIVATE)
                    val edit  : SharedPreferences.Editor = shared.edit()
                    binding.memoFavorite.setBackgroundResource(R.drawable.favorite)
                    binding.memoFavorite.tag = "false"
                    edit.putBoolean(data1.id.toString(), false)
                    edit.apply()
                }
            }
            binding.memoLike.setOnCheckedChangeListener { buttonView, isChecked ->
                CoroutineScope(Dispatchers.IO).launch {
                    memoDB = MemoDB.getInstance(context)
                    memoDB?.MusicDao()?.update(MemoModel(id = data1.id, title = data1.title, content = data1.content, date = data1.date,
                        time = data1.time, like = isChecked))
                }
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
                CoroutineScope(Dispatchers.IO).launch {
                    memoDB = MemoDB.getInstance(context)
                    memoDB?.MusicDao()?.delete(datas[current])
                    deleteItem(current)
                }
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
        val shared = context.getSharedPreferences("sp1", MODE_PRIVATE)
        val edit  : SharedPreferences.Editor = shared.edit()
        edit.putBoolean(datas[position].id.toString(), false)
        edit.apply()
        datas.removeAt(position)
        notifyItemRemoved(position)
    }

}