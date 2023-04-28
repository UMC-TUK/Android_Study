package com.comst19.activityfragment

import android.content.Intent
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

        binding.tv.text = intent.getStringExtra("data")
        Toast.makeText(this,intent.getStringExtra("data"), Toast.LENGTH_LONG).show()

        binding.bakcBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("back","Back")
            setResult(100,intent)
            finish()
        }

        binding.goBtn.setOnClickListener {
            val intent = Intent(this, ThreeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}