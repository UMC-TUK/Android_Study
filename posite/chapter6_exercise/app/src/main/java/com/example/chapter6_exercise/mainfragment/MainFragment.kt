package com.example.chapter6_exercise.mainfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.chapter6_exercise.ImgAdapter
import com.example.chapter6_exercise.R
import com.example.chapter6_exercise.databinding.MainFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }
    private lateinit var adapter: ImgAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgList = arrayListOf(R.drawable.cat1, R.drawable.cat2,
            R.drawable.dog1, R.drawable.dog2)
        adapter = ImgAdapter(imgList)

        binding.imgPager.adapter = adapter
        binding.imgPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageScrollStateChanged(state: Int) {
                // 페이지 스크롤 상태가 변경될 때 실행될 코드 작성
            }
        })
        binding.imgIndicator.setViewPager(binding.imgPager)
        CoroutineScope(Dispatchers.IO).launch{
            while(true){
                Thread.sleep(3000)
                handler.sendEmptyMessage(0)
            }
        }
    }

    private fun setPage(){
        binding.imgPager.setCurrentItem((binding.imgPager.currentItem+1)%4,false)
    }

    //2초 마다 페이지 넘기기


}