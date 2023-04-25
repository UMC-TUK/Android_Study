package com.example.chapter3

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(context: FragmentActivity) : FragmentStateAdapter(context) {
    override fun getItemCount(): Int = fragmentList.size

    private val fragmentList = listOf<Fragment>(Frag1(), Frag2())

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}