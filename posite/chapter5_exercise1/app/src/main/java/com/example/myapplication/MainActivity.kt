package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<SwitchModel>()
        list.apply {
            add(SwitchModel("status 0", false, null))
            add(SwitchModel("status 1", true, null))
            add(SwitchModel("status 2", false, null))
            add(SwitchModel("status 3", true, null))
            add(SwitchModel("status 4", false, null))
            add(SwitchModel("status 5", false, null))
            add(SwitchModel("status 6",  true, null))
            add(SwitchModel("status 7", false, null))

        }

        val switchAdapter = SwitchAdapter(this, list)
        binding.switchList.adapter = switchAdapter
        binding.switchList.layoutManager = LinearLayoutManager(this)
//            totalUserRankingAdapter.notifyDataSetChanged()
        binding.switchList.visibility = View.VISIBLE

        binding.switchList.adapter?.notifyDataSetChanged()
    }
}