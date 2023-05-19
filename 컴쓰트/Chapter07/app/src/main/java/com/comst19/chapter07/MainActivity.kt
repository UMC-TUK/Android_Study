package com.comst19.chapter07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.comst19.chapter07.databinding.ActivityMainBinding
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                binding.resultView.text = "sum : ${msg.arg1}"
            }
        }

        binding.clickBtn.setOnClickListener {
            thread {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..10){
                        sleep(1000)
                        sum += i
                    }

                    val message = Message()
                    message.arg1 = sum.toInt()
                    handler.sendMessage(message)
                }
                Log.d("Handler", "time : $time")
            }
        }


//
//        var flag = false
//
//        binding.start.setOnClickListener {
//            flag = true
//            Thread{
//                while (flag){
//                    runOnUiThread {
//                        binding.img.x += 100
//                        binding.img.y += 50
//                        if(binding.img.x + binding.img.width >= binding.frame.width) binding.img.x = 0f
//                        if(binding.img.y + binding.img.height >= binding.frame.height) binding.img.y = 0f
//                    }
//                    Thread.sleep(100)
//                }
//            }.start()
//        }
//
//        binding.stop.setOnClickListener {
//            flag = false
//        }
//
//        binding.reset.setOnClickListener {
//            binding.img.x = 0f
//            binding.img.y = 0f
//        }
    }
}