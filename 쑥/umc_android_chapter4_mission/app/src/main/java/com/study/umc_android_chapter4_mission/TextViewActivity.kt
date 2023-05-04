package com.study.umc_android_chapter4_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter4_mission.databinding.ActivityTextViewBinding

class TextViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityTextViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra("title")
        binding.context.text = intent.getStringExtra("context")
    }
}