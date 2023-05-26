package com.comst19.chapter08mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.comst19.chapter08mission.databinding.ActivityLikeBinding
import com.comst19.chapter08mission.db.AppDatabase
import com.comst19.chapter08mission.db.MemoDao
import com.comst19.chapter08mission.db.MemoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLikeBinding
    private lateinit var db : AppDatabase
    private lateinit var memoDao : MemoDao
    private lateinit var memoLikeList : ArrayList<MemoEntity>
    private lateinit var adapter : LikeRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLikeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        memoDao = db.getMemoDao()

        getLikeMemoList()
    }

    private fun getLikeMemoList() {
        CoroutineScope(Dispatchers.IO).launch {
            memoLikeList = ArrayList()
            val innerListMemo = db.getMemoDao().getAllMemo()
            for(m in innerListMemo){
                if(m.good){
                    memoLikeList.add(m)
                    db.getMemoDao().updateMemo(m)
                }
            }
            setRecyclerView()
        }
    }

    private fun setRecyclerView(){
        adapter = LikeRVAdapter(memoLikeList)
        binding.memoRV.adapter = adapter
        binding.memoRV.layoutManager = LinearLayoutManager(this)
    }

}