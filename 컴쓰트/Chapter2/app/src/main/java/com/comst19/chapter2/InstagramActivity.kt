package com.comst19.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.comst19.chapter2.databinding.ActivityInstagramBinding

class InstagramActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInstagramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInstagramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.instaTolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.insta_tolbar, menu)
        return true
    }
}