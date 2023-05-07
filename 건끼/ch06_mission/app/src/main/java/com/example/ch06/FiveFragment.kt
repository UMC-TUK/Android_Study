package com.example.ch06

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch06.databinding.FragmentFiveBinding
import com.example.ch06.databinding.FragmentOneBinding
import com.example.ch06.databinding.FragmentThreeBinding

class FiveFragment : Fragment() {
    lateinit var binding: FragmentFiveBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFiveBinding.inflate(inflater, container, false)
        .also {binding = it}.root

    companion object{
        const val TAG = "FiveFragment"
        fun newIntent() = FiveFragment()
    }
}