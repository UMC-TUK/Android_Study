package com.study.umc_android_chapter4

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.study.umc_android_chapter4.databinding.ActivityMainBinding

var saveData : String? = null;

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.send.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            intent.putExtra("text", binding.editText.text.toString())
            startActivity(intent)
        }

    }

    override fun onPause() {
        super.onPause()
        saveData = binding.editText.text.toString()

    }

    override fun onResume() {
        super.onResume()
        if(saveData != null){
            Toast.makeText(this, saveData, Toast.LENGTH_SHORT).show()
            binding.editText.setText(saveData)
        }
        else{
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this)
            .setTitle("이어서 쓸겁니까?")
            .setMessage("앱이 일시정지하여 재시작했습니다. 이전에 작성하시던 내용을 이어서 작성하시겠습니까?")
            .setPositiveButton("네", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                }
            })
            .setNegativeButton("아니오", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    saveData = null
                }
            })
            .create()
            .show()
    }
}