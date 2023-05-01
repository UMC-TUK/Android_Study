package com.study.umc_android_chapter3_mission.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.study.umc_android_chapter3.result_code.ResultCode
import com.study.umc_android_chapter3_mission.R
import com.study.umc_android_chapter3_mission.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendToHost.setOnClickListener {
            setFragmentResult(ResultCode.ONEFRAGMENT, bundleOf("textView" to binding.editText.text.toString()))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}