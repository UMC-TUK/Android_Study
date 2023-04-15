package com.example.chapter3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.chapter3.databinding.FragmentUserFeedBinding

class UserFeedFragment : Fragment() {
    private lateinit var binding: FragmentUserFeedBinding
    private var viewmodel = Viewmodel()
    private val contents = ArrayList<ContentModel>()
    private val types = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_feed, container, false)
        binding.userFeedViewmodel = viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contents.apply {
            add(ContentModel("feed", R.drawable.cat1,1))
            add(ContentModel("feed", R.drawable.cat2,1))
            add(ContentModel("feed", R.drawable.dog1,1))
            add(ContentModel("feed", R.drawable.dog2,1))
            add(ContentModel("feed", R.drawable.fox1,1))
            add(ContentModel("feed", R.drawable.fox2,1))
            add(ContentModel("feed", R.drawable.hamster1,1))
            add(ContentModel("feed", R.drawable.hamster2,1))
        }
        contents.forEach {
            types.add(it.type)
        }

        val gridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)


        binding.contentsImages.layoutManager = gridLayoutManager
        binding.contentsImages.adapter = ThumbnailAdapter(contents, requireContext())
    }

}