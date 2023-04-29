package com.example.ch03_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ch03_mission.databinding.ActivityThreeBinding

class ThreeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Activity3"

        supportFragmentManager.setFragmentResultListener("Three", this)
        { requestKey, bundle ->
            val result = bundle.getString("OneF")
            Toast.makeText(this,"ThreeActivity : $result",Toast.LENGTH_LONG).show()
        }

        binding.fgOneBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, OneFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.fgTwoBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TwoFragment())
                .commit()
        }
    }
}