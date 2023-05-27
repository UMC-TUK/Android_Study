package com.study.umc_chapter8_mission


import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.study.umc_chapter8_mission.adapter.MemoViewAdapter
import com.study.umc_chapter8_mission.database.MemoDatabase
import com.study.umc_chapter8_mission.database.entity.Memo
import com.study.umc_chapter8_mission.databinding.ActivityMainBinding
import com.study.umc_chapter8_mission.result_code.ResultCode
import kotlin.concurrent.thread

lateinit var db : MemoDatabase
lateinit var sharedPreference : SharedPreferences
var data : MutableList<Memo> = mutableListOf()
lateinit var requestLauncher: ActivityResultLauncher<Intent>

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var adapter : MemoViewAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = getSharedPreferences("exist", AppCompatActivity.MODE_PRIVATE)
        db = Room.databaseBuilder(applicationContext, MemoDatabase::class.java, "DictionaryDB").build()

        adapter = MemoViewAdapter(data)
        setMemo()

        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                adapter = MemoViewAdapter(data)
                binding.recyclerview.adapter = adapter
                adapter!!.notifyDataSetChanged()
            }
        }

        requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when (result.resultCode) {
                ResultCode.SAVE_MEMO -> {
                    saveItem(result, adapter)
                }

                ResultCode.EDIT_MEMO -> {
                    editItem(result, adapter)
                }

                ResultCode.CANCLE_MEMO ->
                    Toast.makeText(this, "메모 작성이 취소되었습니다.", Toast.LENGTH_SHORT).show()

                ResultCode.ERROR ->
                    Toast.makeText(this, "데이터 수정 에러1 : 아이템 위치 불러오기를 실패하였습니다.", Toast.LENGTH_SHORT)
                        .show()
            }
        }

        binding.add.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            requestLauncher.launch(intent)
        }

        binding.bookmark.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }


    }

    private fun saveItem(result: ActivityResult, adapter: MemoViewAdapter) {
        val titleData = result.data?.getStringExtra(ResultCode.TITLE)
        val contextData = result.data?.getStringExtra(ResultCode.CONTEXT)
        data.add(Memo(null, titleData.toString(), contextData.toString(), false))
        adapter.notifyItemInserted(adapter.itemCount)
        thread {
            db.memoDao().insert(data[data.size-1])
        }
    }

    private fun editItem(result: ActivityResult, adapter: MemoViewAdapter) {
        val position = result.data?.getIntExtra(ResultCode.POSITION, -1)
        if (position != -1) {
            val titleData = result.data?.getStringExtra(ResultCode.TITLE)
            val contextData = result.data?.getStringExtra(ResultCode.CONTEXT)
            data[position!!].title = titleData.toString()
            data[position].context = contextData.toString()
            adapter.notifyItemChanged(position)
            thread {
                db.memoDao().update(data[position])
                val temp : String? = sharedPreference.getString(data[position].id.toString(), null)
                if(temp != null){
                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.remove(data[position].id.toString())
                    val value = gson.toJson(data[position], object : TypeToken<Memo>(){}.type)
                    val key = data[position].id.toString()

                    Log.d("BookMark", "bookmark edit $position ${data[position].title}")

                    editor.putString(key, value)
                    editor.apply()
                }
            }
        } else {
            Toast.makeText(this, "데이터 수정 에러2 : 아이템 위치 불러오기를 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setMemo(){
        thread {
            Log.d("DB Test", "data set start")
            if(sharedPreference.getBoolean("exist", false)){
                data = db.memoDao().getAll()
                handler.sendEmptyMessage(0)
                Log.d("DB Test", "db read")

            }else{
                Log.d("DB Test", "data make")
                data.add(Memo(null, resources.getString(R.string.app_name), resources.getString(R.string.app_context), false))
                for (i in 0..15) {
                    data.add(Memo(null, "$i", "$i 에 10을 더하면 ${i + 10}", false))
                }
                data.add(
                    Memo(null, resources.getString(R.string.kotlin_title), resources.getString(
                        R.string.kotiln_context), false)
                )
                data.add(Memo(null, resources.getString(R.string.toss), resources.getString(R.string.toss_context), false))
                data.add(
                    Memo(null, resources.getString(R.string.umc_chapter2_title), resources.getString(
                        R.string.umc_chapter2_context), false)
                )
                data.add(
                    Memo(null, resources.getString(R.string.ubuntu_title), resources.getString(
                        R.string.ubuntu_context), false)
                )

                for(i in data.indices){
                    db.memoDao().insert(data[i])
                }

                handler.sendEmptyMessage(0)

                val editor: SharedPreferences.Editor = sharedPreference.edit()

                editor.putBoolean("exist", true)
                editor.apply()
            }
        }
    }

}