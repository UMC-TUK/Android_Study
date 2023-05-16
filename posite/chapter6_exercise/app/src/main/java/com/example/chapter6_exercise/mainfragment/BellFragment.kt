package com.example.chapter6_exercise.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chapter6_exercise.FragmentAdapter
import com.example.chapter6_exercise.databinding.BellFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class BellFragment: Fragment() {
    private lateinit var binding: BellFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BellFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentContent.adapter = FragmentAdapter(this)
        val tabs = arrayOf("전체", "내 소식", "업데이트", "기다무")
        TabLayoutMediator(binding.fragmentTab, binding.fragmentContent) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }


}