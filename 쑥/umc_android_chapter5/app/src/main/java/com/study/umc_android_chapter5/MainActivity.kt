package com.study.umc_android_chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.umc_android_chapter5.adapter.MemoViewAdapter
import com.study.umc_android_chapter5.data.Memo
import com.study.umc_android_chapter5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var data = ArrayList<Memo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data.add(Memo(resources.getString(R.string.app_name), resources.getString(R.string.app_context), R.drawable.blank))
        for(i in 0..5){
            data.add(Memo("$i", "$i 에 10을 더하면 ${i+10}", R.drawable.blank))
        }
        data.add(Memo(resources.getString(R.string.kotlin_title), resources.getString(R.string.kotiln_context), R.drawable.kotlin))
        data.add(Memo(resources.getString(R.string.toss), resources.getString(R.string.toss_context), R.drawable.toss_logo_primary))
        data.add(Memo(resources.getString(R.string.umc_chapter2_title), resources.getString(R.string.umc_chapter2_context), R.drawable.capture))
        data.add(Memo(resources.getString(R.string.ubuntu_title), resources.getString(R.string.ubuntu_context), R.drawable.img_1))
        binding.recyclerview.adapter = MemoViewAdapter(data)
    }
}