package com.example.chapter4_exercise

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.chapter4_exercise.databinding.ActivityMainBinding
import com.example.chapter4_exercise.databinding.FixDialogBinding

class MainActivity : AppCompatActivity() {
    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 0) {
                val memoIntent = it.data
                try {
                    val recvedTitle = memoIntent!!.getStringExtra("title")
                    val receivedContent = memoIntent.getStringExtra("content")
                    if(recvedTitle != null) {
                        title = recvedTitle
                    }
                    if(receivedContent != null) {
                        content = receivedContent
                    }
                    Log.d("변경", "receivedTitle: $recvedTitle  receivedContent: $receivedContent")
                } catch (e: Exception) {

                }

            }
        }
    private var title = ""
    private var content = ""
    private var recentTitle = ""
    private var recentContent = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callback)

        binding.returnBtn.setOnClickListener {
            if(!binding.memoContent.text.isNullOrEmpty()) {
                val intent = Intent(applicationContext, SecondActivity::class.java)
                intent.putExtra("title", binding.memoTitle.text.toString())
                intent.putExtra("content", binding.memoContent.text.toString())
                activityResultLauncher.launch(intent)
            } else {
                Toast.makeText(applicationContext, "제목과 내용을 넣어주세요!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(title.isNotBlank()) {
            binding.memoTitle.setText(title)
        }
        if(content.isNotBlank()) {
            binding.memoContent.setText(content)
        }
    }

    override fun onPause() {
        super.onPause()
        title = binding.memoTitle.text.toString()
        content = binding.memoContent.text.toString()
        if(recentContent.isNotBlank() && recentTitle.isNotBlank()) {
            Log.d("recent", "$recentTitle  $recentContent")
            Toast.makeText(applicationContext, "title $recentTitle  content $recentContent", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("recent", "$recentTitle  $recentContent")
    }

    override fun onRestart() {
        super.onRestart()
        val fixDialog = Dialog(this)
        val dialogBinding = FixDialogBinding.inflate(layoutInflater)
        fixDialog.setContentView(dialogBinding.root)
        dialogBinding.noBtn.setOnClickListener {
            recentContent = content
            recentTitle = title
            title = ""
            content = ""
            binding.memoTitle.setText("")
            binding.memoContent.setText("")
            Log.d("변경 안함", "변경 안함  title: $title  content: $content")
            fixDialog.dismiss()
        }
        dialogBinding.yesBtn.setOnClickListener {
            Log.d("변경 함", "변경함  title: $title  content: $content")
            fixDialog.dismiss()
        }
        fixDialog.show()
    }
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

}