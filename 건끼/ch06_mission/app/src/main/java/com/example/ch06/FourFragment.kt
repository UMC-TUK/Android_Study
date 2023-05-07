package com.example.ch06

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch06.databinding.FragmentFourBinding
import com.example.ch06.databinding.FragmentOneBinding
import com.example.ch06.databinding.FragmentThreeBinding

class FourFragment : Fragment() {
    lateinit var binding: FragmentFourBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFourBinding.inflate(inflater, container, false)
        .also {binding = it}.root

    companion object{
        const val TAG = "FourFragment"
        fun newIntent() = FourFragment()
    }
}