package com.example.ch05_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch05_mission.databinding.ActivityMemoBinding
import com.example.ch05_mission.entity.MemoEntity

class MemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            intent.putExtra("memo", MemoEntity(title = binding.titleT.text.toString(), detail = binding.detailT.text.toString()))
            setResult(RESULT_OK,intent)
            finish()
        }
        if (intent.getSerializableExtra("m")!=null) {
            binding.detailT.setText((intent.getSerializableExtra("m") as MemoEntity).detail)
            binding.titleT.setText((intent.getSerializableExtra("m") as MemoEntity).title)
        }
    }
}