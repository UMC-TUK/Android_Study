package com.example.ch06

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(fragment: Fragment, private val fragmentList: List<Fragment>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =fragmentList.size

    override fun createFragment(position: Int) =fragmentList[position]
}