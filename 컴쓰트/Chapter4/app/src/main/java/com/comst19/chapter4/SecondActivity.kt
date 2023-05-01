package com.comst19.chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = "두 번째 액티비티"

        Toast.makeText(this, intent.getStringExtra("data"), Toast.LENGTH_SHORT).show()
    }
}