package com.study.umc_android_chapter6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.study.umc_android_chapter6.databinding.ActivityMainBinding
import com.study.umc_android_chapter6.fragment.TodayFragment
import com.study.umc_android_chapter6.fragment.HomeFragment
import com.study.umc_android_chapter6.fragment.MypageFragment
import com.study.umc_android_chapter6.fragment.SettingFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame, HomeFragment())
        transaction.commit()
        transaction.addToBackStack(null)
        binding.bottomNavigationView.selectedItemId = R.id.menu_home

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_today -> {
                    val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame, TodayFragment())
                    transaction.commit()
                    transaction.addToBackStack(null)
                }
                R.id.menu_home -> {
                    val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame, HomeFragment())
                    transaction.commit()
                    transaction.addToBackStack(null)
                }
                R.id.menu_mypage -> {
                    val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame, MypageFragment())
                    transaction.commit()
                    transaction.addToBackStack(null)
                }
                R.id.menu_setting -> {
                    val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame, SettingFragment())
                    transaction.commit()
                    transaction.addToBackStack(null)
                }

            }
            true
        }
    }
}
