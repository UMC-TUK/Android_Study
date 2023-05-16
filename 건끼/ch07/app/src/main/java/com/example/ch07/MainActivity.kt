package com.example.ch07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.ch07.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    val handler= Handler(Looper.getMainLooper()){
        runHorse()
        true
    }

    var x1 = 4
    var y1 = -6
    var x2 = 8
    var y2 = -9
    var x3 = -10
    var y3 = 13

    var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hList = listOf(binding.h1, binding.h2, binding.h3)

        binding.add.setOnClickListener {
            hList[i].isVisible = true
            i+=1
            binding.sub.isEnabled =true
            if (i ==3){
                binding.add.isEnabled =false
            }
        }
        binding.sub.setOnClickListener {
            i-=1
            binding.add.isEnabled =true
            hList[i].isGone = true
            if (i ==0){
                binding.sub.isEnabled =false
            }
        }

        val thread = Thread(HorseRunnable())
        thread.start()
    }

    inner class HorseRunnable :Runnable{
        override fun run() {
            while(true){
                try {
                    Thread.sleep(100)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException){
                    Log.d("interupt", "interupt발생")
                }
            }
        }
    }

    private fun runHorse(){
        binding.h1.x += x1
        binding.h1.y += y1
        binding.h2.x += x2
        binding.h2.y += y2
        binding.h3.x += x3
        binding.h3.y += y3
        if (binding.h1.x > binding.background.width - binding.h1.width || binding.h1.x < binding.background.x)
            x1 = -x1
        if (binding.h1.y > binding.background.height - binding.h1.height || binding.h1.y < binding.background.y)
           y1 = -y1
        if (binding.h2.x > binding.background.width - binding.h2.width || binding.h2.x < binding.background.x)
            x2 = -x2
        if (binding.h2.y > binding.background.height - binding.h2.height || binding.h2.y < binding.background.y)
            y2 = -y2
        if (binding.h3.x > binding.background.width - binding.h3.width || binding.h3.x < binding.background.x)
            x3 = -x3
        if (binding.h3.y > binding.background.height - binding.h3.height || binding.h3.y < binding.background.y)
            y3 = -y3
    }
}