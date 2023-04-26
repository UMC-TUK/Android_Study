package com.example.ch02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch02.databinding.FragmentBenefitBinding
import com.example.ch02.databinding.FragmentHomeBinding

class BenefitFragment: Fragment() {
    lateinit var binding : FragmentBenefitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBenefitBinding.inflate(inflater, container, false).also { binding =it }.root

    companion object {
        const val TAG = "BenefitFragment"
        fun newInstance() = BenefitFragment()
    }
}