package com.example.ch06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ch06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(OneFragment.newIntent(),OneFragment.TAG)
        binding.bottomMenu.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.one -> {
                    showFragment(OneFragment.newIntent(),OneFragment.TAG)
                    true
                }
                R.id.two -> {
                    showFragment(TwoFragment.newIntent(),TwoFragment.TAG)
                    true
                }
                R.id.three ->{
                    showFragment(ThreeFragment.newIntent(), ThreeFragment.TAG)
                    true
                }
                else -> {false}
            }
        }
    }

    fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run{
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
}