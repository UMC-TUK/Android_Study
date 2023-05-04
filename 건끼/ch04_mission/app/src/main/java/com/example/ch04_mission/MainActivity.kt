package com.example.ch04_mission

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.ch04_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    var memo = ""
    var toggle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            intent = Intent(this, CheckActivity::class.java)
            intent.putExtra("memo",binding.editText.text.toString())
            startActivity(intent)
        }
        binding.boldBtn.setOnClickListener {
            if (!toggle) {
                binding.editText.setTypeface(null, Typeface.BOLD)
                toggle = true
            }
            else {
                binding.editText.setTypeface(null, Typeface.NORMAL)
                toggle =false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume")
        binding.editText.setText(memo)
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause")
        memo = binding.editText.text.toString()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle","onRestart")
        AlertDialog.Builder(this)
            .setTitle("메모")
            .setMessage("이어서 작성하시겠습니까?")
            .setPositiveButton("네",null)
            .setNegativeButton("아니요", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    memo = ""
                    binding.editText.setText(memo) //restart 에서 기다리지 않고 바로 resume으로 넘어가기 때문에
                }
            })
            .create()
            .show()
    }
}