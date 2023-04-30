package com.study.umc_android_chapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.study.umc_android_chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("umc", "onCreate()")

        binding.button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("title", binding.title.text.toString())
            intent.putExtra("context", binding.context.text.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("umc", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("umc", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("umc", "onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("umc", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("umc", "onDestroy()")
    }
}