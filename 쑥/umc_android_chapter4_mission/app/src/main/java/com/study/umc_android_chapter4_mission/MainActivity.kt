package com.study.umc_android_chapter4_mission

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.study.umc_android_chapter4_mission.databinding.ActivityMainBinding

var saveTitle : String = ""
var saveContext : String = ""

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            intent.putExtra("title", binding.title.text.toString())
            intent.putExtra("context", binding.context.text.toString())
            startActivity(intent)
        }

    }

    override fun onPause() {
        super.onPause()
        saveTitle = binding.title.text.toString()
        saveContext = binding.context.text.toString()

    }

    override fun onResume() {
        super.onResume()
        if((saveTitle.isNotBlank()) or (saveContext.isNotBlank())){
            binding.title.setText(saveTitle)
            binding.context.setText(saveContext)
        }

    }

    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this)
            .setTitle("이어서 쓸겁니까?")
            .setMessage("앱이 일시정지하여 재시작했습니다. 이전에 작성하시던 내용을 이어서 작성하시겠습니까?")
            .setPositiveButton("네"
            ) { _, _ -> }
            .setNegativeButton("아니오"
            ) { _, _ ->
                saveTitle = ""
                saveContext = ""
                binding.title.setText(saveTitle)
                binding.context.setText(saveContext)
            }
            .setNeutralButton("내용만 지우기")
            { _, _ ->
                saveContext = ""
                binding.context.setText(saveContext)
            }
            .create()
            .show()
    }
}