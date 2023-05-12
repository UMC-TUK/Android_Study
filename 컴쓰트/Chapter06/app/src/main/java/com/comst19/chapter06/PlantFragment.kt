package com.comst19.chapter06

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.comst19.chapter06.databinding.FragmentPlantBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PlantFragment : Fragment() {

    private var _binding : FragmentPlantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPlantBinding.inflate(inflater, container, false)

        val adapter = PlantFragmentViewPagerAdapter(requireActivity())

        binding.plantViewPager.adapter = adapter

        val tabName = arrayOf<String>("Flower", "Tree", "Grass")

        TabLayoutMediator(binding.tabLayout, binding.plantViewPager) {tab, position ->
            tab.text = tabName[position]
        }.attach()

        binding.tabLayout.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.plantViewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        return _binding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}