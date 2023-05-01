package com.comst19.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.comst19.chapter5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        datas.add("a")
        datas.add("b")
        datas.add("c")
        datas.add("d")
        datas.add("e")

        val rv = RVAdapter(datas)
        binding.RV.adapter = rv
        binding.RV.layoutManager = LinearLayoutManager(this)

    }
}