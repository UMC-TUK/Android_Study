package com.example.chapter8_exercise

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chapter8_exercise.databinding.ActivityFavoriteStorageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteStorageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteStorageBinding
    private var memoDB: MemoDB? = null
    private lateinit var memoListAdapter: MemoListAdapter
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.onBackPressedDispatcher.addCallback(callback)
        val coroutine = CoroutineScope(Dispatchers.Main)
        memoDB = MemoDB.getInstance(applicationContext)
        val ids = intent.getIntArrayExtra("ids")
        if (ids != null) {
            val memos = mutableListOf<MemoModel>()
            coroutine.launch {
                CoroutineScope(Dispatchers.IO).async {
                    for(i in ids) {
                        val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
                        if(sharedPreference.getBoolean(i.toString(), false)) {
                            val memo = memoDB?.MusicDao()?.getMemo(i)
                            if (memo != null) {
                                memos.add(memo)
                            }
                        }
                    }

                }.await()
                initRecycler(memos)
            }

        }

    }

    private fun initRecycler(list: List<MemoModel>) {
        Log.d("init", "init recycler")
        Log.d("init", "크기: $list")

        val gridLayoutManager = GridLayoutManager(applicationContext, 3)
        val sortedList = list.sortedWith(compareBy<MemoModel> {it.date}.thenBy { it.time }).toMutableList()
        sortedList.reverse()
        Log.d("sorted", "sorted: $sortedList")

        memoListAdapter = MemoListAdapter(this, sortedList)
        binding.memoList.layoutManager = gridLayoutManager
        binding.memoList.adapter = memoListAdapter
        binding.memoList.visibility = View.VISIBLE
        memoListAdapter.notifyDataSetChanged()
        Log.d("memo1", "갯수" +binding.memoList.adapter?.itemCount.toString())
    }
}