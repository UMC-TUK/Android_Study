package com.example.chapter8_exercise

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chapter8_exercise.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0
    private var ids = listOf<Int>()
    private var memoDB: MemoDB? = null
    private lateinit var memoListAdapter: MemoListAdapter

    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 0) {
                val coroutine = CoroutineScope(Dispatchers.Main)
                coroutine.launch {
                    val result = coroutine.async(Dispatchers.IO) {
                        memoDB?.MusicDao()?.getMemos()
                    }.await()
                    if(result != null) {
                        if(result.isNotEmpty()) {
                            initRecycler(result)
                        }
                    }
                }

            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coroutine = CoroutineScope(Dispatchers.Main)
        memoDB = MemoDB.getInstance(applicationContext)
        coroutine.launch {
            val result = coroutine.async(Dispatchers.IO) {
                memoDB?.MusicDao()?.getMemos()
            }.await()
            if(result != null) {
                if(result.isNotEmpty()) {
                    initRecycler(result)
                }
            }
        }

        val popupView = LayoutInflater.from(this).inflate(R.layout.fab_description, null)
        val popup = PopupWindow(
            popupView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        ).apply {
            isFocusable = true
            elevation = 10f
        }

        binding.floatingWriteBtn.setOnClickListener {
            val intent = Intent(applicationContext, MemoActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        binding.floatingWriteBtn.setOnLongClickListener {
            popup.showAsDropDown(binding.floatingWriteBtn, -100, -(binding.floatingWriteBtn.height+150))
            Handler(Looper.getMainLooper()).postDelayed({
                if (popup.isShowing) {
                    popup.dismiss()
                }
            }, 3000)
            true
        }
        binding.favoriteStorage.setOnClickListener {
            val intent = Intent(applicationContext, FavoriteStorageActivity::class.java)
            val idArray: IntArray = ids.toIntArray()
            intent.putExtra("ids" ,idArray)
            startActivity(intent)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val result = coroutine.async(Dispatchers.IO) {
                memoDB?.MusicDao()?.getMemos()
            }.await()
            if(result != null) {
                if(result.isNotEmpty()) {
                    initRecycler(result)
                }
            }
        }
    }

    private fun initRecycler(list: List<MemoModel>) {
        Log.d("init", "init recycler")
        Log.d("init", "크기: $list")

        val gridLayoutManager = GridLayoutManager(applicationContext, 3)
        val sortedList = list.sortedWith(compareBy<MemoModel> {it.date}.thenBy { it.time }).toMutableList()
        sortedList.reverse()
        ids = sortedList.map { it.id }
        Log.d("sorted", "sorted: $sortedList")

        memoListAdapter = MemoListAdapter(applicationContext, sortedList)
        binding.memoList.layoutManager = gridLayoutManager
        binding.memoList.adapter = memoListAdapter
        binding.memoList.visibility = View.VISIBLE
        memoListAdapter.notifyDataSetChanged()
        Log.d("memo1", "갯수" +binding.memoList.adapter?.itemCount.toString())
        count = sortedList.size
    }
}