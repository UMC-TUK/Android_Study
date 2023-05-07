package com.study.umc_android_chapter5_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.study.umc_android_chapter5_mission.adapter.MemoViewAdapter
import com.study.umc_android_chapter5_mission.data.Memo
import com.study.umc_android_chapter5_mission.databinding.ActivityMainBinding
import com.study.umc_android_chapter5_mission.result_code.ResultCode
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var data = LinkedList<Memo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data.add(Memo(resources.getString(R.string.app_name), resources.getString(R.string.app_context), false))
        for(i in 0..15){
            data.add(Memo("$i", "$i 에 10을 더하면 ${i+10}", false))
        }
        data.add(Memo(resources.getString(R.string.kotlin_title), resources.getString(R.string.kotiln_context), false))
        data.add(Memo(resources.getString(R.string.toss), resources.getString(R.string.toss_context), false))
        data.add(Memo(resources.getString(R.string.umc_chapter2_title), resources.getString(R.string.umc_chapter2_context), false))
        data.add(Memo(resources.getString(R.string.ubuntu_title), resources.getString(R.string.ubuntu_context), false))
        var adapter = MemoViewAdapter(data)
        binding.recyclerview.adapter = adapter

        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            when(result.resultCode){
                ResultCode.SAVE_MEMO -> {
                    saveItem(result, adapter)
                }
                ResultCode.EDIT_MEMO->{
                    editItem(result, adapter)
                }
                ResultCode.CANCLE_MEMO->
                    Toast.makeText(this, "메모 작성이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                ResultCode.ERROR->
                    Toast.makeText(this, "데이터 수정 에러1 : 아이템 위치 불러오기를 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.setItemClickListener(object : MemoViewAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val item = data[position]
                val intent = Intent(v.context, MemoActivity::class.java)
                intent.putExtra(ResultCode.TITLE, item.title)
                intent.putExtra(ResultCode.CONTEXT, item.context)
                intent.putExtra(ResultCode.POSITION, position)
                intent.putExtra(ResultCode.EDIT_CHECK, true)
                requestLauncher.launch(intent)
            }
        })

        adapter.setItemLongClickListener(object : MemoViewAdapter.OnItemLongClickListener{
            override fun onLongClick(v: View, position: Int): Boolean {
                data.removeAt(position)
                adapter.notifyDataSetChanged()
                return true
            }

        })

        binding.add.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            requestLauncher.launch(intent)
        }
    }

    private fun saveItem(result : ActivityResult, adapter: MemoViewAdapter){
        val titleData = result.data?.getStringExtra(ResultCode.TITLE)
        val contextData = result.data?.getStringExtra(ResultCode.CONTEXT)
        data.add(Memo(titleData.toString(), contextData.toString(), false))
        adapter.notifyItemInserted(adapter.itemCount)
    }

    private fun editItem(result: ActivityResult, adapter: MemoViewAdapter){
        val positon = result.data?.getIntExtra(ResultCode.POSITION, -1)
        if(positon != -1){
            val titleData = result.data?.getStringExtra(ResultCode.TITLE)
            val contextData = result.data?.getStringExtra(ResultCode.CONTEXT)
            data[positon!!].title=titleData.toString()
            data[positon].context=contextData.toString()
            adapter.notifyItemChanged(positon)
        }else{
            Toast.makeText(this, "데이터 수정 에러2 : 아이템 위치 불러오기를 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}