package com.comst19.activityfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.comst19.activityfragment.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    private var _binding : FragmentOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOneBinding.inflate(inflater,container,false)

        binding.messageBtn.setOnClickListener {
            val data = binding.edit.text.toString()
            setFragmentResult("data", bundleOf("bundleKey" to data))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}