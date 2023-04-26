package com.example.chapter3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chapter3.databinding.FragmentFrag1Binding

class Frag1 : Fragment() {

    private lateinit var binding: FragmentFrag1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFrag1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.frag1Button.setOnClickListener {
            Toast.makeText(requireContext(), "Frag1 Button!!", Toast.LENGTH_SHORT).show()
        }
    }
}