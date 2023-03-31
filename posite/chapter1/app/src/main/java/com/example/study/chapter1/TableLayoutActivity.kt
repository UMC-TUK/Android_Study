package com.example.study.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.study.R
import com.example.study.databinding.ActivityTableLayoutBinding

class TableLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTableLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_layout)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}