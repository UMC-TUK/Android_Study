package com.example.ch04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ch04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle","onCreate()")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sandBtn.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("edit",binding.editText.text.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume()")
    }

    override fun onPause() {

        super.onPause()
        Log.d("lifecycle", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy()")
    }

}