package com.example.chapter3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chapter3.databinding.FragmentFrag2Binding


class Frag2 : Fragment() {
    private lateinit var binding: FragmentFrag2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFrag2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.frag2Button.setOnClickListener {
            Toast.makeText(requireContext(), "Frag2 Button!!", Toast.LENGTH_SHORT).show()
        }
    }

}