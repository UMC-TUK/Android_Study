package com.example.chapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.example.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle4", "Main onCreate()")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.onBackPressedDispatcher.addCallback(this, callback)

        binding.sendText.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("text", binding.inputText.text.toString())
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle4", "Main onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle4", "Main onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle4", "Main onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle4", "Main onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle4", "Main onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle4", "Main onDestroy()")
    }
}