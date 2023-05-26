package com.comst19.chapter08mission

import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter08mission.databinding.ActivityMainBinding
import com.comst19.chapter08mission.databinding.DialogAddBinding
import com.comst19.chapter08mission.databinding.ListItemMemoBinding
import com.comst19.chapter08mission.databinding.MenuDialogBinding
import com.comst19.chapter08mission.db.AppDatabase
import com.comst19.chapter08mission.db.MemoDao
import com.comst19.chapter08mission.db.MemoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MemoRVAdapter(private var memoList : ArrayList<MemoEntity>) : RecyclerView.Adapter<MemoRVAdapter.MyViewHolder>() {

    private lateinit var db : AppDatabase
    private lateinit var menuDialog : AlertDialog

    fun addListItem(memoItem : MemoEntity){
        memoList.add(0, memoItem)
    }

    fun menuDialogShow(binding : ListItemMemoBinding, memo: MemoEntity, position: Int){
        val bindingMenuDialog = MenuDialogBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)

        menuDialog = AlertDialog.Builder(binding.root.context).setView(bindingMenuDialog.root).show()
        bindingMenuDialog.editBtn.setOnClickListener {

            val bindingDialog = DialogAddBinding.inflate(LayoutInflater.from(binding.root.context), binding.root,false )
            bindingDialog.etMemo.setText(memo.memoContent)

            AlertDialog.Builder(binding.root.context)
                .setTitle("Memo 수정하기")
                .setView(bindingDialog.root)
                .setPositiveButton("수정") { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val innerListMemo = db.getMemoDao().getAllMemo()
                        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
                        for (m in innerListMemo) {
                            if (m.memoContent == memo.memoContent && m.memoDate == memo.memoDate) {
                                m.memoContent = bindingDialog.etMemo.text.toString()
                                m.memoDate = date
                                db.getMemoDao().updateMemo(m)
                            }
                        }

                        memo.memoContent = bindingDialog.etMemo.text.toString()
                        memo.memoDate = date

                        memoList.set(position, memo)
                        (binding.root.context as Activity).runOnUiThread {
                            notifyDataSetChanged()
                            Toast.makeText(binding.root.context, "메모가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .setNegativeButton("취소") { _, _ -> }
                .show()
            menuDialog.dismiss()
        }
        bindingMenuDialog.deleteBtn.setOnClickListener {
            AlertDialog.Builder(binding.root.context)
                .setTitle("[주의]")
                .setMessage("제거하시면 데이터는 복구되지 않습니다!\n정말 제거하시겠습니까?")
                .setPositiveButton("제거") { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val innerListMemo = db.getMemoDao().getAllMemo()
                        for (m in innerListMemo) {
                            if (m.memoContent == memo.memoContent && m.memoDate == memo.memoDate) {
                                db.getMemoDao().deleteMemo(m)
                            }
                        }
                        memoList.remove(memo)
                        (binding.root.context as Activity).runOnUiThread {
                            notifyDataSetChanged()
                            Toast.makeText(binding.root.context, "제거되었습니다", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
                .setNegativeButton("취소") { _, _ ->

                }
                .show()
            menuDialog.dismiss()
        }
    }

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
                    (binding.root.context as Activity).runOnUiThread {
                        notifyDataSetChanged()
                    }
                }
            }

            binding.root.setOnLongClickListener {
                menuDialogShow(binding, memo, adapterPosition)
                true
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