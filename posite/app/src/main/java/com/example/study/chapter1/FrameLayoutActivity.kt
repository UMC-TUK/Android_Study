package com.example.study.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.study.R
import com.example.study.databinding.ActivityFrameLayoutBinding

class FrameLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrameLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_frame_layout)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.img1 -> {
                    binding.frameDog1.visibility = View.VISIBLE
                    binding.frameDog2.visibility = View.INVISIBLE
                }
                R.id.img2 -> {
                    binding.frameDog1.visibility = View.INVISIBLE
                    binding.frameDog2.visibility = View.VISIBLE
                }
            }
            true
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}