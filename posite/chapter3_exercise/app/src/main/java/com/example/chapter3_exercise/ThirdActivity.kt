package com.example.chapter3_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chapter3_exercise.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val frag1 = FirstFrag()
        val frag2 = SecondFrag()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, frag1)
            .add(R.id.content_frame, frag2)
            .hide(frag2)
            .commit()

        binding.firstBtn.setOnClickListener {
            val transactionN = supportFragmentManager.beginTransaction()
            transactionN.hide(frag2)
                .show(frag1)
                .commit()
        }

        binding.secondBtn.setOnClickListener {
            val transactionN = supportFragmentManager.beginTransaction()
            transactionN.hide(frag1)
                .show(frag2)
                .commit()
        }

        supportFragmentManager
            .setFragmentResultListener("return", this) { requestKey, bundle ->
                val result = bundle.getString("bundle")
                Log.d("result", "requestKey: $requestKey  result : $result")
                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            }
    }
}