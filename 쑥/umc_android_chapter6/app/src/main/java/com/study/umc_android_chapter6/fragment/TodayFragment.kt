package com.study.umc_android_chapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.umc_android_chapter6.databinding.FragmentTodayBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodayFragment : Fragment() {
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private lateinit var date : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        date = setDate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.today.text = date


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDate() : String {
        val today = LocalDateTime.now()
        val sdf = DateTimeFormatter.ofPattern("yyyy.MM.dd\n HH:mm:ss")
        return today.format(sdf)
    }

}