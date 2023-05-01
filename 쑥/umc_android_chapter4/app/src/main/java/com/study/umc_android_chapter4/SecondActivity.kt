package com.study.umc_android_chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter4.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra("title")
        binding.context.text = intent.getStringExtra("context")
    }
}