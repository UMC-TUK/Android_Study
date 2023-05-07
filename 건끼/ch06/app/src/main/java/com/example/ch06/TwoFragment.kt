package com.example.ch06

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch06.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTwoBinding.inflate(inflater, container, false)
        .also { binding=it }.root

    companion object {
        const val TAG = "TwoFragment"
        fun newIntent() = TwoFragment()
    }
}