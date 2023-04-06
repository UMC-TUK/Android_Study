package com.example.ch02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.example.ch02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.scrollView.smoothScrollTo(0,0)
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {

            if (binding.scrollView.scrollY>binding.scrollView.height- 450f.dpToPx(this).toInt()){
                binding.motionLayout.transitionToEnd()
            }else{
                binding.motionLayout.transitionToStart()
            }
        }
    }

    fun Float.dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            context.resources.displayMetrics
        )
    }
}