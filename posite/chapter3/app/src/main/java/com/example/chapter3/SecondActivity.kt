package com.example.chapter3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chapter3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = intent.getStringExtra("text")
        if(text != null) {
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
            binding.intentResult.text = "Intent success!!"
        } else {
            Toast.makeText(applicationContext, "Intent fail!!", Toast.LENGTH_SHORT).show()
            binding.intentResult.text = "Intent fail!!"
        }

        binding.returnMain.setOnClickListener {
            val intentW = Intent(applicationContext, MainActivity::class.java)
            intentW.putExtra("return","success!!")
            setResult(0, intentW)
            finish()
        }
    }

}