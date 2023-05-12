package com.study.umc_android_chapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.umc_android_chapter6.R
import com.study.umc_android_chapter6.adapter.PostViewAdapter
import com.study.umc_android_chapter6.data.Post
import com.study.umc_android_chapter6.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = ArrayList<Post>()

        data.add(Post(resources.getString(R.string.app_name), resources.getString(R.string.app_context), R.drawable.blank))
        for(i in 0..20){
            data.add(Post("$i 번째 제목", "$i 에 10을 더하면 ${i+10} 입니다.\n이거 너무 자주 쓰죠. 하지만 얘만큼 편한 게 없는 것 같아요.", R.drawable.apple))
        }

        var adapter = PostViewAdapter(data)
        binding.recyclerview.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}