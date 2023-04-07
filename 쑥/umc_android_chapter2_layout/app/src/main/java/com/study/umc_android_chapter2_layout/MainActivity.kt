package com.study.umc_android_chapter2_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.umc_android_chapter2.adapter.AppADViewAdapter
import com.study.umc_android_chapter2_layout.data.AppAD
import com.study.umc_android_chapter2_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var appADList : ArrayList<AppAD>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appADList = ArrayList(4)

        appADList.add(AppAD("알람설정", "놓쳤나요?", R.drawable.alram))
        appADList.add(AppAD("알람설정", "놓쳤나요?", R.drawable.alram))
        appADList.add(AppAD("알람설정", "놓쳤나요?", R.drawable.alram))
        appADList.add(AppAD("더보기", "인기", R.drawable.alram))

        val appADViewAdapter = AppADViewAdapter(appADList)
        appADViewAdapter.notifyDataSetChanged()

        binding.horizontalRecyclerview.adapter = appADViewAdapter
        binding.horizontalRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}