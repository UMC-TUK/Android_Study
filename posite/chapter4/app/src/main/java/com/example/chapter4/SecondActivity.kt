package com.example.chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chapter4.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = intent.getStringExtra("text")
        binding.receivedText.text = text.toString()

        Log.d("lifecycle4", "second  onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle4", "second  onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle4", "second  onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle4", "second  onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle4", "second  onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle4", "second  onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle4", "second  onDestroy()")
    }
}