package com.example.ch05_mission

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.ch05_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var memo = mutableListOf<MemoEntity>()
   
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==101 && resultCode== RESULT_OK){
            memo.add(data?.getSerializableExtra("memo") as MemoEntity)
            memoAdapter.setDataList(memo)
        }
    }
}
