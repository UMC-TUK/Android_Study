package com.example.chapter2_exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.chapter2_exercise.databinding.FragmentUserReelsBinding


class UserReelsFragment : Fragment() {
    private lateinit var binding: FragmentUserReelsBinding
    private var viewmodel = Viewmodel()
    private val contents = ArrayList<ContentModel>()
    private val types = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_reels, container, false)
        binding.userReelsViewmodel = viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contents.apply {
            add(ContentModel("reels", R.drawable.cat1,1))
            add(ContentModel("reels", R.drawable.cat2,1))
            add(ContentModel("reels", R.drawable.dog1,1))
            add(ContentModel("reels", R.drawable.dog2,1))
            add(ContentModel("reels", R.drawable.fox1,1))
            add(ContentModel("reels", R.drawable.fox2,1))
            add(ContentModel("reels", R.drawable.hamster1,1))
            add(ContentModel("reels", R.drawable.hamster2,1))
        }
        contents.forEach {
            types.add(it.type)
        }

        val gridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)


        binding.contentsImages.layoutManager = gridLayoutManager
        binding.contentsImages.adapter = ThumbnailAdapter(contents, requireContext())
    }
}