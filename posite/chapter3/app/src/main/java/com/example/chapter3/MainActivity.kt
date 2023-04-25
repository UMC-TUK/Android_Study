package com.example.chapter3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.chapter3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 0) {
                val walletIntent = it.data
                try {
                    val result = walletIntent!!.getStringExtra("return")
                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()


                } catch (e: Exception) {

                }

            } else if (it.resultCode == 1) {

            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userContent.adapter = FragmentAdapter(this)
        binding.userContent.isUserInputEnabled = true
        val tabs = arrayOf(R.drawable.baseline_looks_one_24, R.drawable.baseline_looks_two_24)
        TabLayoutMediator(binding.userTab, binding.userContent) { tab, position ->
            tab.setIcon(tabs[position])
        }.attach()
        binding.goSecond.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("text", binding.goSecond.text.toString())
            activityResultLauncher.launch(intent)
        }
    }
}