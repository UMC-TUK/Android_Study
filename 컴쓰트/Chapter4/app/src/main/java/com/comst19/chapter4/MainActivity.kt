package com.comst19.chapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.comst19.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "첫 번째 액티비티"

        binding.btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", "첫 번째 액티비티에서 온 메시지")
            startActivity(intent)
        }

        Log.d("Lifecycle","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","onDestroy")
    }
}