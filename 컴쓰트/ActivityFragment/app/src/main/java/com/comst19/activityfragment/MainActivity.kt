package com.comst19.activityfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.comst19.activityfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val oneFragment = OneFragment()
    private val twoFragment = TwoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            intent.putExtra("data", "메인 엑티비티에서 보낸 메시지")
            startActivity(intent)
        }
        binding.btn2.setOnClickListener {
            replaceFragment(oneFragment)
        }
        binding.btn3.setOnClickListener {
            replaceFragment(twoFragment)
        }
    }

    private fun replaceFragment(fragment : Fragment){

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,fragment)
            commit()
        }

    }
}