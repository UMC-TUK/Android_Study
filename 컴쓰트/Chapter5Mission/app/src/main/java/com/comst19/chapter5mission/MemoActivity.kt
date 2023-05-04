package com.comst19.chapter5mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.comst19.chapter5mission.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edit.setText(intent.getStringExtra("modify"))

        binding.saveBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("memo",binding.edit.text.toString())
            setResult(100, intent)
            finish()
        }
    }
}