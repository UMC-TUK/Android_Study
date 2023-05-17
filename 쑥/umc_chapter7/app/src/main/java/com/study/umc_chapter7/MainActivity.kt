package com.study.umc_chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.study.umc_chapter7.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var handler : Handler
    lateinit var thread: Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                if(msg.what == 0){
                    val cal = Calendar.getInstance()

                    val sdf = SimpleDateFormat("HH:mm:ss")
                    val strTime = sdf.format(cal.time)

                    binding.time.text = strTime
                }else{
                    binding.time.text = null
                }
            }
        }

        binding.start.setOnClickListener {
            thread = thread(true) {
                try {
                    while (true) {
                        Thread.sleep(1000)
                        handler?.sendEmptyMessage(0)
                    }
                } catch (e: InterruptedException) {
                    Log.d("time", "중단")
                    handler?.sendEmptyMessage(1)
                }
            }
        }

        binding.stop.setOnClickListener {
            thread.interrupt()
        }

    }
}