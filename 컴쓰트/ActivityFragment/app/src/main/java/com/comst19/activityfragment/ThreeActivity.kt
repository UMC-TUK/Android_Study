package com.comst19.activityfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.comst19.activityfragment.databinding.ActivityThreeBinding

class ThreeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityThreeBinding
    private val oneFragment = OneFragment()
    private val twoFragment = TwoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            replaceFragment(oneFragment)
        }
        binding.btn2.setOnClickListener {
            replaceFragment(twoFragment)
        }

        supportFragmentManager.setFragmentResultListener("data",this){ requestKey, bundle ->
            Toast.makeText(this, bundle.getString("bundleKey"), Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment : Fragment){

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,fragment)
            commit()
        }

    }
}