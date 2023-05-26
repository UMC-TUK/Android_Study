package com.comst19.chapter08mission

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.comst19.chapter08mission.databinding.ActivityMainBinding
import com.comst19.chapter08mission.databinding.DialogAddBinding
import com.comst19.chapter08mission.db.AppDatabase
import com.comst19.chapter08mission.db.MemoDao
import com.comst19.chapter08mission.db.MemoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var db : AppDatabase
    private lateinit var memoDao : MemoDao
    private lateinit var memoList : ArrayList<MemoEntity>
    private lateinit var adapter : MemoRVAdapter
    //private lateinit var dialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        memoDao = db.getMemoDao()

        getAllMemoList()

        binding.writeBtn.setOnClickListener {
            addDialogShow()
        }

        binding.likeList.setOnClickListener {
            val intent = Intent(this, LikeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllMemoList() {
        CoroutineScope(Dispatchers.IO).launch {
            memoList = ArrayList(memoDao.getAllMemo())
            setRecyclerView()
        }
    }

    private fun setRecyclerView(){
        adapter = MemoRVAdapter(memoList)
        binding.memoRV.adapter = adapter
        binding.memoRV.layoutManager = LinearLayoutManager(this)
    }

    private fun addDialogShow(){
        val bindingDialog = DialogAddBinding.inflate(LayoutInflater.from(binding.root.context), binding.root,false )

        AlertDialog.Builder(this)
            .setTitle("Memo 남기기")
            .setView(bindingDialog.root)
            .setPositiveButton("저장") { _, _ ->
                val memo = bindingDialog.etMemo.text.toString()
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
                val like = false
                CoroutineScope(Dispatchers.IO).launch {
                    memoDao.insertMemo(MemoEntity(null, memo, date, like))
                    adapter.addListItem(MemoEntity(null, memo, date, like))
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            .setNegativeButton("취소") { _, _ -> }
            .show()
    }

}