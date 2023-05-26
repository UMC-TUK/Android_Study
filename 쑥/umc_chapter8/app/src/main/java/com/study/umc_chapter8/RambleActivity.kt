package com.study.umc_chapter8

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.study.umc_chapter8.adapter.DictionaryAdapter
import com.study.umc_chapter8.databinding.ActivityRambleBinding
import com.study.umc_chapter8.entity.Dictionary
import kotlin.concurrent.thread

class RambleActivity : AppCompatActivity() {
    lateinit var binding : ActivityRambleBinding
    private lateinit var data : MutableList<Dictionary>
    private lateinit var sharedPreference : SharedPreferences
    private lateinit var handler: Handler
    lateinit var adapter : DictionaryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRambleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = mutableListOf()
        sharedPreference = getSharedPreferences("set", MODE_PRIVATE)

        setThread()

        adapter = DictionaryAdapter(data)
        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                for(i in 0 until data.size){
                    Log.d("resume test", "$i====${data[i].english}")
                }
                adapter = DictionaryAdapter(data)
                binding.recyclerview.adapter = adapter
                adapter!!.notifyDataSetChanged()
            }
        }

        binding.add.setOnClickListener {
            data.add(Dictionary(data.size, "add", "추가"))
            thread {
                db.dictionaryDao().insert(data[data.size-1])
            }
            adapter.notifyItemInserted(data.size)
        }

        binding.allChange.setOnClickListener {
            thread {
                for(i in 0 until data.size){
                    data[i].english = "a"
                    data[i].korean = "아"

                    db.dictionaryDao().update(data[i])
                }
            }
            adapter.notifyDataSetChanged()
        }

        binding.delteLast.setOnClickListener {
            thread {
                db.dictionaryDao().delete(data[data.size-1])
                data.removeAt(data.size-1)
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun setThread() {
        thread {
            if(sharedPreference.getBoolean("set", false)){
                data = db.dictionaryDao().getAll()

                for(i in 0 until data.size){
                    Log.d("test", "$i====${data[i].english}")
                }

                handler.sendEmptyMessage(0)

            }else{
                data.add(Dictionary(1, "culture", "문화"))
                data.add(Dictionary(2, "experience", "경험"))
                data.add(Dictionary(3, "giraffe", "기린"))
                data.add(Dictionary(4, "hawk", "매"))
                data.add(Dictionary(5, "liberty", "자유"))
                data.add(Dictionary(6, "affair", "사건"))
                data.add(Dictionary(7, "pill", "알약"))
                data.add(Dictionary(8, "competition", "경쟁"))

                for(i in 0 until data.size){
                    db.dictionaryDao().insert(data[i])
                    Log.d("test", "$i====${data[i].english}")
                }

                handler.sendEmptyMessage(0)

                val editor: SharedPreferences.Editor = sharedPreference.edit()

                editor.putBoolean("set", true)
                editor.commit()
            }
        }
    }
}