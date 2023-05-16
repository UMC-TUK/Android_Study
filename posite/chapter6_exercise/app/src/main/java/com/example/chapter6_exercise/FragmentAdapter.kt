package com.example.chapter6_exercise

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter6_exercise.innerfragment.MyNewsFragment
import com.example.chapter6_exercise.innerfragment.TotalFragment
import com.example.chapter6_exercise.innerfragment.UpdateFragment
import com.example.chapter6_exercise.innerfragment.WaitFragment

class FragmentAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragmentList.size

    private val fragmentList = listOf<Fragment>(TotalFragment(), MyNewsFragment(), UpdateFragment(), WaitFragment())

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}