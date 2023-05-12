package com.comst19.chapter06

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.comst19.chapter06.databinding.FragmentHomeBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.Math.ceil

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homes = mutableListOf<Int>(R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeViewPager.adapter = HomeFragmentViewPagerAdapter(homes)
        binding.homeViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.homeIndicator.setViewPager(binding.homeViewPager)

        binding.homeIndicator.createIndicators(4,0)

//        lifecycleScope.launch {
//            while(isActive){
//                delay(1000L)
//                val currentPage = binding.homeViewPager.currentItem
//                val totalPages = binding.homeViewPager.adapter?.itemCount ?: 0
//                val nextPage = (currentPage + 1) % totalPages
//                binding.homeViewPager.setCurrentItem(nextPage, true)
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            while(isActive){
                delay(1000L)
                val currentPage = binding.homeViewPager.currentItem
                val totalPages = binding.homeViewPager.adapter?.itemCount ?: 0
                val nextPage = (currentPage + 1) % totalPages
                binding.homeViewPager.setCurrentItem(nextPage, false)
            }
        }


        return _binding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}