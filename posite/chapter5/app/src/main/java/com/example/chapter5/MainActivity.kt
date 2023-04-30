package com.example.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val members = ArrayList<DataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        members.apply{
            add(DataModel("포짓", "Android"))
            add(DataModel("컴쓰트", "Android"))
            add(DataModel("건끠", "Android"))
            add(DataModel("쑥", "Android"))
        }
        binding.mainRv.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            adapter = MemberAdapter(members, context)
        }

    }
}