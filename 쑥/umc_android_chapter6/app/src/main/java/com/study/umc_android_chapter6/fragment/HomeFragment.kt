package com.study.umc_android_chapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.adapter.AdViewAdapter
import com.study.umc_android_chapter6.adapter.PostViewAdapter
import com.study.umc_android_chapter6.data.Ad
import com.study.umc_android_chapter6.data.Post
import com.study.umc_android_chapter6.databinding.FragmentHomeBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var job : Job
    var viewPagerPosition = 0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ad = ArrayList<Ad>()
        val data = ArrayList<Post>()
        data.add(Post(resources.getString(R.string.app_name), resources.getString(R.string.app_context), R.drawable.blank))
        for(i in 0..20){
            data.add(Post("$i 번째 제목", "$i 에 10을 더하면 ${i+10} 입니다.\n이거 너무 자주 쓰죠. 하지만 얘만큼 편한 게 없는 것 같아요.", R.drawable.apple))
        }
        ad.add(Ad(resources.getString(R.string.ad), R.drawable.naver, R.drawable.ad_image))
        ad.add(Ad("이건 희망사항입니다.\n부캠 해주면 안되나...?", R.drawable.toss_logo_madeinme, R.drawable.ad_toss_image))
        ad.add(Ad("여러분이 생각하는\n그 기업 맞습니다.", R.drawable.carrot, R.drawable.ad_carrot_image))
        ad.add(Ad("당근 아이콘  제작자: Freepik - Flaticon", R.drawable.carrot, R.drawable.ad_toss_image))
        ad.add(Ad("전 슬라이드는 \n당근 이미지 저작권 표시", R.drawable.blank, R.drawable.ad_image))
        ad.add(Ad("이건 제가 그렸어요\n희망사항을 담아서", R.drawable.toss_logo_madeinme, R.drawable.ad_carrot_image))

        val recyclerViewAdapter = PostViewAdapter(data)
        val viewPagerAdapter = AdViewAdapter(ad)
        binding.recyclerview.adapter = recyclerViewAdapter
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPagerPosition = 0

        binding.viewPager.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPagerPosition = position

                binding.itemPosition.text = "${(viewPagerPosition % ad.size)+1} / ${ad.size}"
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state){
                    ViewPager2.SCROLL_STATE_IDLE -> {if (!job.isActive) scrollJobCreate()}
                    ViewPager2.SCROLL_STATE_DRAGGING -> {
                        job.cancel()
                    }
                }
            }
        })
        //binding.indicator.setViewPager(binding.viewPager)
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(3500)
            if(viewPagerPosition == 5){
                viewPagerPosition = -1
            }
            binding.viewPager.setCurrentItem(++viewPagerPosition, true)
        }
    }
}