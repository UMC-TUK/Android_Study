package com.study.umc_android_chapter6.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.study.umc_android_chapter6.fragment.OneFragment
import com.study.umc_android_chapter6.fragment.ThreeFragment
import com.study.umc_android_chapter6.fragment.TwoFragment

class ViewPagerAdapter(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when(position){
            0 -> OneFragment()
            1-> TwoFragment()
            else-> ThreeFragment()
    }
}