package com.comst19.chapter2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import com.comst19.chapter2.databinding.ActivityTossBinding

class TossActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTossBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTossBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tossTolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.scrollView.smoothScrollBy(0,0)
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {

            if(binding.scrollView.scrollY > binding.scrollView.height - 1000){
                binding.motionLayout.transitionToEnd()
            }else{
                binding.motionLayout.transitionToStart()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toss_tolbar, menu)
        return true
    }

}