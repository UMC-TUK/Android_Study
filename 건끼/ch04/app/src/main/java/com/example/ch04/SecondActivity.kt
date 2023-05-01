package com.example.ch04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ch04.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle","[2] onCreate()")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = intent.getStringExtra("edit")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "[2] onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "[2] onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "[2] onResume()")
    }

    override fun onPause() {

        super.onPause()
        Log.d("lifecycle", "[2] onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "[2] onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "[2] onDestroy()")
    }
}