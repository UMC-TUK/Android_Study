package com.study.umc_android_chapter3_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter3.result_code.ResultCode
import com.study.umc_android_chapter3_mission.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    lateinit var binding : ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "두 번째 액티비티"

        binding.textView.text = intent.getStringExtra("result")

        binding.send.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("toast", "Back")
            setResult(ResultCode.SUBACTIVITY, intent)
            finish()
        }

        binding.goFragmentActivity.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }
}