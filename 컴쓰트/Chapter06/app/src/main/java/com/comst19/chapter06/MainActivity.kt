package com.comst19.chapter06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.comst19.chapter06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val cameraFragment = CameraFragment()
    private val homeFragment = HomeFragment()
    private val plantFragment = PlantFragment()
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(cameraFragment)

        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_camera -> {
                    replaceFragment(cameraFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    replaceFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_plant -> {
                    replaceFragment(plantFragment)
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}