package com.example.ch02

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch02.data.CountEntity
import com.example.ch02.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater,container,false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scrollAnimation()
        countContainerClick()
    }

    private fun scrollAnimation(){
        binding.scrollView.smoothScrollTo(0,0)
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {

            if (binding.scrollView.scrollY>binding.scrollView.height- 450f.dpToPx(requireContext()).toInt()){
                binding.motionLayout.transitionToEnd()
            }else{
                binding.motionLayout.transitionToStart()
            }
        }
    }

    private fun countContainerClick() {
        binding.countContainer.setOnClickListener {
            startActivity(RemitActivity.newIntent(requireContext(),
                CountEntity(binding.assetNameTextView.text.toString(),binding.moneyTextView.text.toString())))
        }
        binding.countContainer1.setOnClickListener {
            startActivity(RemitActivity.newIntent(requireContext(),
                CountEntity(binding.assetNameTextView1.text.toString(),binding.moneyTextView1.text.toString())))
        }
        binding.countContainer2.setOnClickListener {
            startActivity(RemitActivity.newIntent(requireContext(),
                CountEntity(binding.assetNameTextView2.text.toString(),binding.moneyTextView2.text.toString())))
        }
    }

    fun Float.dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            context.resources.displayMetrics
        )
    }

    companion object {
        const val TAG = "HomeFragment"
        const val KEY = "HOME"
        fun newInstance() = HomeFragment()
    }
}