package com.example.ch05_mission

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.room.Room
import com.example.ch05_mission.databinding.ActivityMainBinding
import com.example.ch05_mission.entity.MemoEntity

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var memo = mutableListOf<MemoEntity>()
    lateinit var db: AppDatabase

     val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            memo.add(it.data?.getSerializableExtra("memo") as MemoEntity)
            memoAdapter.setDataList(memo)
        }
    }


    private var memoAdapter = MemoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"memoDB").fallbackToDestructiveMigration().build()

        binding.boxBtn.setOnClickListener{
            intent = Intent(this, BoxActivity::class.java)
            startActivity(intent)
        }
        memoAdapter.db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "memoDB").fallbackToDestructiveMigration().build()
        memoAdapter.clickListener={
            memoAdapter.clickListener= {
                memo.remove(it)
                intent = Intent(this, MemoActivity::class.java)
                intent.putExtra("m", it)
                startActivityForResult(intent, 101)
            }
        }
        binding.addBtn.setOnClickListener {
            intent = Intent(this, MemoActivity::class.java)
            launcher.launch(intent)
        }
        binding.clearBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                memoAdapter.clickListener = {
                    memo.remove(it)
                    memoAdapter.setDataList(memo)
                    if(it.liked){
                        Thread(Runnable {
                            db.memoDao().deleteMemo(it.title)
                        }).start()
                    }
                }
            }else{
                memoAdapter.clickListener= {
                    memo.remove(it)
                    intent = Intent(this, MemoActivity::class.java)
                    intent.putExtra("m", it)
                    startActivityForResult(intent, 101)
                }
            }
        }

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL,true)
            adapter = memoAdapter
        }
        Thread(Runnable {
            db.memoDao().getAll().run {
                runOnUiThread {
                    memoAdapter.setDataList(this)
                }
            }
        }).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==101 && resultCode== RESULT_OK){
            memo.add(data?.getSerializableExtra("memo") as MemoEntity)
            memoAdapter.setDataList(memo)
        }
    }
}
