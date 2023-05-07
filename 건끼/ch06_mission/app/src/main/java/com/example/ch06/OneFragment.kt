package com.example.ch06

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch06.databinding.FragmentOneBinding
import com.google.android.material.tabs.TabLayoutMediator

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentOneBinding.inflate(inflater, container, false)
        .also {binding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter =
            FragmentPagerAdapter(this, listOf( FourFragment.newIntent(),FiveFragment.newIntent(), SixFragment.newIntent()))

        binding.viewPager.adapter = fragmentAdapter
        val tabTitles = listOf<String>("Fragment4", "Fragment5","Fragment6" )
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }


    companion object{
        const val TAG = "OneFragment"
        fun newIntent() = OneFragment()
    }
}