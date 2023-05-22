package com.example.chapter8

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
        if(sharedPreference.getInt("number", 0) != 0) {
            binding.callNumber.text = sharedPreference.getInt("number", 0).toString()
        }
        var editor  : SharedPreferences.Editor = sharedPreference.edit()


        binding.saveNumber.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()) {
                editor.putInt("number", binding.inputText.text.toString().toInt())
                editor.apply()
                editor = sharedPreference.edit()
                binding.callNumber.text = sharedPreference.getInt("number", 0).toString()
            }
        }
    }
}