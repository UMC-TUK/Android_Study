package com.example.ch03_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ch03_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    val activityTwoLauncher : ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            Toast.makeText(this, "${it.data?.getStringExtra("finish")}",Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Activity 1"

        binding.button.setOnClickListener {
            intent = Intent(this, TwoActivity::class.java)
            intent.putExtra("edit",binding.edit.text.toString())
            activityTwoLauncher.launch(intent)
        }
    }
}