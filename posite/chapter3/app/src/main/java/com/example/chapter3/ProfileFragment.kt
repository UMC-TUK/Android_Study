package com.example.chapter3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.chapter3.databinding.ProfileFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment: Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    private var viewmodel = Viewmodel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.profileFragViewmodel = viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userContent.adapter = UserFeedAdapter(this)
        binding.userContent.isUserInputEnabled = true
        val tabs = arrayOf(R.drawable.grid, R.drawable.video, R.drawable.avatar)
        TabLayoutMediator(binding.userTab, binding.userContent) { tab, position ->
            tab.setIcon(tabs[position])
        }.attach()

    }


}