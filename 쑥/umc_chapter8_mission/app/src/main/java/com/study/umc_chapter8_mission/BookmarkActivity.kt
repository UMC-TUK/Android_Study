package com.study.umc_chapter8_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.study.umc_chapter8_mission.adapter.MemoViewAdapter
import com.study.umc_chapter8_mission.database.entity.Memo
import com.study.umc_chapter8_mission.databinding.ActivityBookmarkBinding
import kotlin.concurrent.thread

var gson: Gson = GsonBuilder().create()

class BookmarkActivity : AppCompatActivity() {
    lateinit var bookmarks : MutableList<Memo>
    lateinit var adapter : MemoViewAdapter
    lateinit var binding: ActivityBookmarkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = getSharedPreferences("exist", AppCompatActivity.MODE_PRIVATE)
        bookmarks = setMemo()
        adapter = MemoViewAdapter(bookmarks)
        binding.recyclerview.adapter = adapter
    }

    private fun setMemo() : MutableList<Memo>{
        val bookmark = mutableListOf<Memo>()
        thread {
            for(i in 0 until data.size){
                var temp : String? = sharedPreference.getString(data[i].id.toString(), null)
                if(temp != null){
                    bookmark.add(gson.fromJson(temp, object : TypeToken<Memo>(){}.type))
                    Log.d("BookMark", "not null! $i ${bookmark[bookmark.size-1].title}")
                }
            }
        }

        return bookmark
    }
}