package com.comst19.activityfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.comst19.activityfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val oneFragment = OneFragment()
    private val twoFragment = TwoFragment()

    private lateinit var requestLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == 100){
                val data = it.data
                Toast.makeText(this,data!!.getStringExtra("back"),Toast.LENGTH_SHORT).show()
            }
        }

        binding.btn1.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            intent.putExtra("data",  binding.edit.text.toString())
            requestLauncher.launch(intent)
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