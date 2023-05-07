package com.study.umc_android_chapter5_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter5_mission.databinding.ActivityMemoBinding
import com.study.umc_android_chapter5_mission.result_code.ResultCode

class MemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoBinding
    var position : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var editCheck = intent.getBooleanExtra(ResultCode.EDIT_CHECK, false)

        if(editCheck){
            position = intent.getIntExtra(ResultCode.POSITION, -1)
            if(position == -1){
                setResult(ResultCode.ERROR)
                finish()
            }
            binding.title.setText(intent.getStringExtra(ResultCode.TITLE))
            binding.context.setText(intent.getStringExtra(ResultCode.CONTEXT))
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent()
            setResult(ResultCode.CANCLE_MEMO, intent)
            finish()
        }
        binding.save.setOnClickListener {
            if (editCheck){
                val intent = Intent()
                intent.putExtra(ResultCode.TITLE, binding.title.text.toString())
                intent.putExtra(ResultCode.CONTEXT, binding.context.text.toString())
                intent.putExtra(ResultCode.POSITION, position)
                setResult(ResultCode.EDIT_MEMO, intent)
                finish()
            }else{
                val intent = Intent()
                intent.putExtra(ResultCode.TITLE, binding.title.text.toString())
                intent.putExtra(ResultCode.CONTEXT, binding.context.text.toString())
                setResult(ResultCode.SAVE_MEMO, intent)
                finish()
            }
        }
    }
}