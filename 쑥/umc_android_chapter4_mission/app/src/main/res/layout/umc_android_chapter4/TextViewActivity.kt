package com.study.umc_android_chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter4.databinding.ActivityTextViewBinding

class TextViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityTextViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.TextView.text = intent.getStringExtra("text")
    }
}