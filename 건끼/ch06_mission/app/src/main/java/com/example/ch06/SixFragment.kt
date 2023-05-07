package com.example.ch06

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch06.databinding.FragmentFiveBinding
import com.example.ch06.databinding.FragmentSixBinding

class SixFragment : Fragment() {
    lateinit var binding: FragmentSixBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSixBinding.inflate(inflater, container, false)
        .also {binding = it}.root

    companion object{
        const val TAG = "SixFragment"
        fun newIntent() = SixFragment()
    }
}