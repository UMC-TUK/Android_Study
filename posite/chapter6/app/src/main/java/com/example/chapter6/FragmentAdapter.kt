package com.example.chapter6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter6.innerfragment.InnerFirstFragment
import com.example.chapter6.innerfragment.InnerFourthFragment
import com.example.chapter6.innerfragment.InnerSecondFragment
import com.example.chapter6.innerfragment.InnerThirdFragment

class FragmentAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragmentList.size

    private val fragmentList = listOf<Fragment>(InnerFirstFragment(), InnerSecondFragment(), InnerThirdFragment(), InnerFourthFragment())

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}