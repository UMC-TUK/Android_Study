package com.study.umc_android_chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.study.umc_android_chapter3.databinding.ActivityFragmentBinding
import com.study.umc_android_chapter3.fragment.OneFragment
import com.study.umc_android_chapter3.fragment.TwoFragment

class FragmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "두 번째 액티비티"

        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_content, OneFragment())
        transaction.commit()
        transaction.addToBackStack(null)

        binding.textView.text = intent.getStringExtra("result")

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