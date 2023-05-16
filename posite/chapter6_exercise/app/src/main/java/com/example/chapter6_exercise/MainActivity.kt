package com.example.chapter6_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter6_exercise.databinding.ActivityMainBinding
import com.example.chapter6_exercise.mainfragment.BellFragment
import com.example.chapter6_exercise.mainfragment.GiftFragment
import com.example.chapter6_exercise.mainfragment.MainFragment
import com.example.chapter6_exercise.mainfragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarTitle.text = "Main"

        val frag1 = MainFragment()
        val frag2 = SearchFragment()
        val frag3 = BellFragment()
        val frag4 = GiftFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.contentFrame.id, frag1)
            .add(binding.contentFrame.id, frag2)
            .add(binding.contentFrame.id, frag3)
            .add(binding.contentFrame.id, frag4)
            .hide(frag2)
            .hide(frag3)
            .hide(frag4)
            .commit()

        binding.bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.main_home -> {
                    binding.toolbarTitle.text = "Main"
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(frag1)
                        .hide(frag2)
                        .hide(frag3)
                        .hide(frag4)
                        .commit()
                }
                R.id.main_search -> {
                    binding.toolbarTitle.text = "Search"
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(frag2)
                        .hide(frag1)
                        .hide(frag3)
                        .hide(frag4)
                        .commit()
                }
                R.id.main_book -> {
                    binding.toolbarTitle.text = "Bell"
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(frag3)
                        .hide(frag2)
                        .hide(frag1)
                        .hide(frag4)
                        .commit()
                }
                R.id.main_clean -> {
                    binding.toolbarTitle.text = "Gift"
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(frag4)
                        .hide(frag2)
                        .hide(frag3)
                        .hide(frag1)
                        .commit()
                }
            }
            true
        }
    }
}