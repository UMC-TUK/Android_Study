package com.study.umc_android_chapter3_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.study.umc_android_chapter3.result_code.ResultCode
import com.study.umc_android_chapter3_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "첫 번째 액티비티"

        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == ResultCode.SUBACTIVITY) {
                val resultData = result.data?.getStringExtra("toast")
                Toast.makeText(this, resultData, Toast.LENGTH_SHORT).show()
            }
        }



        binding.button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("result", "${binding.editText.text}")
            setResult(ResultCode.MAINACTIVITY, intent)
            requestLauncher.launch(intent)
        }
    }
}