package com.example.study.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.study.R
import com.example.study.databinding.ActivityGridLayoutBinding

class GridLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid_layout)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}