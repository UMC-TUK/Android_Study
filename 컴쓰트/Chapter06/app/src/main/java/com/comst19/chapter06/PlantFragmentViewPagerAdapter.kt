package com.comst19.chapter06

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PlantFragmentViewPagerAdapter(activity: FragmentActivity ) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FlowerFragment()
            1 -> TreeFragment()
            2 -> GrassFragment()
            else -> FlowerFragment()
        }
    }

}