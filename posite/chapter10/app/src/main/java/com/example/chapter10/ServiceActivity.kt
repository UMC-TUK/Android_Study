package com.example.chapter10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter10.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra("id")?.let {
            binding.userId.text = it
        }
        intent.getStringExtra("url")?.let {
            binding.userAddress.text = it
        }

    }
}