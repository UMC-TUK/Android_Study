package com.comst19.chapter5mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.comst19.chapter5mission.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edit.setText(intent.getStringExtra("modify"))
        binding.saveBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("modify",binding.edit.text.toString())
            intent.putExtra("position", intent.getIntExtra("position",0))
            setResult(200,intent)
            finish()
        }
    }
}