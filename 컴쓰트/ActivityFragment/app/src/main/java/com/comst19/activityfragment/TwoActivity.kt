package com.comst19.activityfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.comst19.activityfragment.databinding.ActivityTwoBinding

class TwoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this,intent.getStringExtra("data"), Toast.LENGTH_LONG).show()
    }
}