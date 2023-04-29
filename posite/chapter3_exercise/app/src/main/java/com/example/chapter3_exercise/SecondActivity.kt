package com.example.chapter3_exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter3_exercise.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val text = intent.getStringExtra("text")!!
            binding.receiveText.text = text
        } catch (e: Exception) {
            binding.receiveText.text = "받은 값 없음!!"
        }
        binding.sendText.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("return", "back")
            setResult(0, intent)
            finish()
        }
        binding.goThird.setOnClickListener {
            val intent = Intent(applicationContext, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

}