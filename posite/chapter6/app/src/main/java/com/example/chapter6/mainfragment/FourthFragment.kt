package com.example.chapter6.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chapter6.FragmentAdapter
import com.example.chapter6.R
import com.example.chapter6.databinding.FragmentFourthBinding
import com.google.android.material.tabs.TabLayoutMediator

class FourthFragment : Fragment() {
    private lateinit var binding: FragmentFourthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentContent.adapter = FragmentAdapter(this)
        val tabs = arrayOf(
            R.drawable.circle1,
            R.drawable.circle2,
            R.drawable.circle3,
            R.drawable.circle4
        )
        TabLayoutMediator(binding.fragmentTab, binding.fragmentContent){tab, position ->
            tab.setIcon(tabs[position])
        }.attach()
    }

}