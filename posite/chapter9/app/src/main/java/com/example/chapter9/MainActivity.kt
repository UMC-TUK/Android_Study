package com.example.chapter9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter9.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var page = 1
    private val line = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val deferred = coroutine.async(Dispatchers.IO) {
                ApiRepository().getTrain(page, line)
            }
            val result = deferred.await()
            if(result?.data != null) {
                Log.d("data","data: ${result.data}")
                val trainData = result.data
                binding.trainFrame.adapter = TrainAdapter(trainData)
                binding.trainFrame.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.trainFrame.adapter?.notifyDataSetChanged()
            }

        }
    }
}