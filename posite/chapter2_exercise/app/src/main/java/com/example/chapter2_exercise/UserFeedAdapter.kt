package com.example.chapter2_exercise


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class UserFeedAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragmentList.size

    private val fragmentList = listOf<Fragment>(UserFeedFragment(), UserReelsFragment(), UserActivityFragment())

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}