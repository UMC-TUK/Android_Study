package com.study.umc_android_chapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.umc_android_chapter6.adapter.SettingViewAdapter
import com.study.umc_android_chapter6.data.Setting
import com.study.umc_android_chapter6.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var setting = ArrayList<Setting>()

        for(i in 1..20){
            setting.add(Setting("설정$i"))
        }

        var adapter = SettingViewAdapter(setting)
        binding.recyclerview.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}