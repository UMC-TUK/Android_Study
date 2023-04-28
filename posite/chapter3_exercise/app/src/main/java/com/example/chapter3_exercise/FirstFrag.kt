package com.example.chapter3_exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.chapter3_exercise.databinding.FragmentFirstBinding

class FirstFrag : Fragment() {
    private  lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        binding.sendContent.setOnClickListener{
            val result = binding.inputThird.text.toString()
            this.setFragmentResult("return", bundleOf("bundle" to result))
        }
    }


}