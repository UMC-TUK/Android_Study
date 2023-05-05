package com.comst19.chapter5mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter5mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),itemClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>
    private var memos : MutableList<String> = mutableListOf()
    private lateinit var adapter : RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for( i in 1..15){
            memos.add("Memo $i")
        }

        adapter = RVAdapter(memos, this)
        binding.RV.adapter = adapter
        binding.RV.layoutManager = LinearLayoutManager(this)

        requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == 100){
                val datas = it.data
                memos.add(datas!!.getStringExtra("memo").toString())
                adapter.notifyDataSetChanged()
            }
            if (it.resultCode == 200){
                val datas = it.data
                val position = datas!!.getIntExtra("position",0)
                memos.set(position,datas!!.getStringExtra("modify").toString())
                adapter.notifyDataSetChanged()
            }
        }

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            requestLauncher.launch(intent)
        }
    }

    override fun onClick(position: Int, v : View) {

        val popupMenu = PopupMenu(this, v)
        menuInflater.inflate(R.menu.list_item_popup, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.modify -> {
                    val intent = Intent(this, ModifyActivity::class.java)
                    intent.putExtra("modify", memos[position])
                    intent.putExtra("position",position)
                    requestLauncher.launch(intent)
                    return@setOnMenuItemClickListener true
                }
                R.id.delete -> {
                    memos.removeAt(position)
                    adapter.notifyDataSetChanged()
                    return@setOnMenuItemClickListener true
                }
                else->{ return@setOnMenuItemClickListener false}

            }
        }
    }
}