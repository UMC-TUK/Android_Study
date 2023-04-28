package com.example.ch02

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch02.data.CountEntity
import com.example.ch02.databinding.ActivityRemitBinding

class RemitActivity : AppCompatActivity() {
    lateinit var binding : ActivityRemitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title =""
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews(){
        binding.countName.text = intent.getParcelableExtra<CountEntity>(HomeFragment.KEY)?.Name.toString()
        binding.money.text = intent.getParcelableExtra<CountEntity>(HomeFragment.KEY)?.money.toString()
    }

    companion object{
        fun newIntent(context: Context, entity: CountEntity ) = Intent(context, RemitActivity::class.java).apply {
            putExtra(HomeFragment.KEY,entity)
        }
    }
}