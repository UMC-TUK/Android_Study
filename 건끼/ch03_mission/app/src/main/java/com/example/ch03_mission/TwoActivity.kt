package com.example.ch03_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.ch03_mission.databinding.ActivityTwoBinding

class TwoActivity : AppCompatActivity() {
    lateinit var binding : ActivityTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Activity2"

        binding.textView.text = intent.getStringExtra("edit")
        binding.button.setOnClickListener {
            intent = Intent(this, ThreeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            intent.putExtra("finish","Back")
            setResult(RESULT_OK,intent)
        }
        return super.onKeyDown(keyCode, event)
    }
}