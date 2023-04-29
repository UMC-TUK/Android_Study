package com.study.umc_android_chapter3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "첫 번째 액티비티"

        binding.button.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            intent.putExtra("result", "${binding.editText.text}")
            startActivity(intent)
        }
    }
}