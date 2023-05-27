package com.example.chapter8_exercise

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import com.example.chapter8_exercise.databinding.ActivityMemoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding
    private var memoDB: MemoDB? = null
    private val callback = object : OnBackPressedCallback(true) {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun handleOnBackPressed() {
            if(binding.memoContent.text.isNullOrEmpty() && binding.memoTitle.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "제목, 내용 둘중 하나를 입력해주세요!!", Toast.LENGTH_SHORT).show()
            } else {
                val currentDate = currentDate()
                val title: String = if(binding.memoTitle.text.isNotBlank()) {
                    binding.memoTitle.text.toString()
                } else {
                    ""
                }
                val content: String = if(binding.memoContent.text.isNotBlank()) {
                    binding.memoContent.text.toString()
                } else {
                    ""
                }

                val memo = MemoModel(title = title, content = content, time = currentDate[1], date = currentDate[0], like = false)
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("memo", memo)
                Log.d("memo1", "memo previous: $previous")
                intent.putExtra("previous", previous)
                setResult(0, intent)
                finish()
            }
        }
    }

    private var previous: MemoModel? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val coroutine = CoroutineScope(Dispatchers.IO)

        previous = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("memo", MemoModel::class.java)
        } else {
            (intent.getSerializableExtra("memo") as MemoModel?)
        }

        previous?.let {
            Log.d("memo1", "memo previous: $previous")
            binding.memoTitle.setText(it.title.toString())
            binding.memoContent.setText(it.content.toString())
        }
//        this.onBackPressedDispatcher.addCallback(callback)
        binding.returnBtn.setOnClickListener {
            if(binding.memoContent.text.isNullOrEmpty() && binding.memoTitle.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "제목, 내용 둘중 하나를 입력해주세요!!", Toast.LENGTH_SHORT).show()
            } else {
                val currentDate = currentDate()
                val title: String = if(binding.memoTitle.text.isNotBlank()) {
                    binding.memoTitle.text.toString()
                } else {
                    ""
                }
                val content: String = if(binding.memoContent.text.isNotBlank()) {
                    binding.memoContent.text.toString()
                } else {
                    ""
                }

                val memo = MemoModel(title = title, content = content, time = currentDate[1], date = currentDate[0], like = false)
                val intent = Intent(applicationContext, MainActivity::class.java)
                CoroutineScope(Dispatchers.IO).launch{
                    memoDB = MemoDB.getInstance(applicationContext)
                    intent.putExtra("memo", memo)
                    if(previous != null) {
                        coroutine.async {
                            memoDB?.MusicDao()?.update(MemoModel(id= previous!!.id, title = title,content = content, time = currentDate[1],
                                date = currentDate[0], like = previous!!.like)  )
                        }.await()
                        Log.d("memo1", "memo previous: $previous")
                        intent.putExtra("previous", previous)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                    } else {
                        coroutine.async {
                            memoDB?.MusicDao()?.insert(MemoModel( title = title,content = content, time = currentDate[1],
                                date = currentDate[0], like = false) )
                        }.await()
                        Log.d("memo1", "memo previous: $previous")
                        setResult(0, intent)
                        finish()
                    }
                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun currentDate(): List<String> {
        val current = LocalDateTime.now()
        val formatter1 = DateTimeFormatter.ofPattern("yyyy:MM:dd")
        val formatter2 = DateTimeFormatter.ofPattern("HH:mm")
        val formatDate = current.format(formatter1)
        val formatTime = current.format(formatter2)
        return listOf(formatDate, formatTime)
    }

}