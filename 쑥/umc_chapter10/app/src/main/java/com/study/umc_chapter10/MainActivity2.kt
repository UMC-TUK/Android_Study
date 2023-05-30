package com.study.umc_chapter10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_chapter10.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.accountData.text = "email : ${intent.getStringExtra("email")}\nphotoUrl : ${intent.getStringExtra("photoUrl")}"
    }
}