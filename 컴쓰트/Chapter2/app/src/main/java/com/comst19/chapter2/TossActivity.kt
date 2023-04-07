package com.comst19.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class TossActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toss)

        setSupportActionBar(findViewById(R.id.tossTolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toss_tolbar, menu)
        return true
    }
}