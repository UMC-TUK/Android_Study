package com.example.ch05_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.ch05_mission.databinding.ActivityBoxBinding

class BoxActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoxBinding
    lateinit var db : AppDatabase
    private var memoAdapter = MemoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,true)
            adapter = memoAdapter
        }


        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java ,"memoDB").fallbackToDestructiveMigration().build()
        Thread(Runnable {
            db.memoDao().getAll().run {
                runOnUiThread {
                    memoAdapter.setDataList(this)
                }
            }
        }).start()

    }
}