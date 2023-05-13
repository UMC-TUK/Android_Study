package com.study.umc_android_chapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.adapter.ImageViewAdapter
import com.study.umc_android_chapter6.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = ArrayList<Int>()

        data.add(R.drawable.ad_image)
        data.add(R.drawable.ad_image)
        data.add(R.drawable.ad_image)
        data.add(R.drawable.apple)
        data.add(R.drawable.apple)
        data.add(R.drawable.apple)
        data.add(R.drawable.naver)
        data.add(R.drawable.calendar)
        data.add(R.drawable.calendar)
        data.add(R.drawable.icon)
        data.add(R.drawable.icon)
        data.add(R.drawable.icon)

        var adapter = ImageViewAdapter(data)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}