package com.study.umc_android_chapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.study.umc_android_chapter6.adapter.ViewPagerAdapter
import com.study.umc_android_chapter6.databinding.FragmentMypageBinding
import com.study.umc_android_chapter6.databinding.TabView2Binding
import com.study.umc_android_chapter6.databinding.TabView3Binding
import com.study.umc_android_chapter6.databinding.TabViewBinding

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter


        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            when(position){
                0 -> {
                    tab.customView = TabViewBinding.inflate(layoutInflater).tabTitle
                }
                1 -> {
                    tab.customView = TabView2Binding.inflate(layoutInflater).tabTitle
                }
                2 -> {
                    tab.customView = TabView3Binding.inflate(layoutInflater).tabTitle
                }
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}