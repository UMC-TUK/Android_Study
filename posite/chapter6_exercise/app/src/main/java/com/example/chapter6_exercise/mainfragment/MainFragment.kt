package com.example.chapter6_exercise.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chapter6_exercise.ImgAdapter
import com.example.chapter6_exercise.R
import com.example.chapter6_exercise.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgList = arrayListOf(R.drawable.cat1, R.drawable.cat2, R.drawable.dog1, R.drawable.dog2)
        binding.imgPager.adapter = ImgAdapter(imgList)
        binding.imgIndicator.setViewPager(binding.imgPager)
    }


}