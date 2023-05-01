package com.study.umc_android_chapter3_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.study.umc_android_chapter3.result_code.ResultCode
import com.study.umc_android_chapter3_mission.databinding.ActivityFragmentBinding
import com.study.umc_android_chapter3_mission.fragment.OneFragment
import com.study.umc_android_chapter3_mission.fragment.TwoFragment

class FragmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "세 번째 액티비티"

        supportFragmentManager
            .setFragmentResultListener(ResultCode.ONEFRAGMENT, this) { _, bundle ->
                val result = bundle.getString("textView")
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }

        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_content, OneFragment())
        transaction.commit()
        transaction.addToBackStack(null)

        binding.oneBtn.setOnClickListener {
            val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content, OneFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.twoBtn.setOnClickListener {
            val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content, TwoFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}