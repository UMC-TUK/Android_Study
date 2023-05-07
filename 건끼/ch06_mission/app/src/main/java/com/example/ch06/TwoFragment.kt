package com.example.ch06

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.ch06.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding

    var imgAdapter = ImageAdapter()

    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTwoBinding.inflate(inflater, container, false)
        .also { binding=it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgAdapter.data = listOf<Int>(R.drawable.img1, R.drawable.img2, R.drawable.img3)
        binding.viewPager2.adapter = imgAdapter
        binding.indicate.setViewPager(binding.viewPager2)
        val thread = Thread(PagerRunnable())
        thread.start()
    }

    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                try {
                    Thread.sleep(3000)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException){
                    Log.d("interupt", "interupt발생")
                }
            }
        }
    }

    private fun setPage(){
        binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem, true)
        if(binding.viewPager2.currentItem == 2){
            binding.viewPager2.currentItem = 0
        }else{
            binding.viewPager2.currentItem+=1
        }
    }

    companion object {
        const val TAG = "TwoFragment"
        fun newIntent() = TwoFragment()
    }
}