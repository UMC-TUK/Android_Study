package com.example.chapter2_exercise

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter2_exercise.databinding.MainFragmentBinding

class MainFragment: Fragment() {
    private lateinit var binding: MainFragmentBinding
    private var viewmodel = Viewmodel()
    private var reelsList = ArrayList<ReelsModel>()
    private var feedList = ArrayList<FeedListModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.mainFragViewmodel = viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reelsList.apply{
            add(ReelsModel(R.drawable.dog1, "posite"))
            add(ReelsModel(R.drawable.dog2, "comst"))
            add(ReelsModel(R.drawable.cat1, "쑥"))
            add(ReelsModel(R.drawable.cat2, "건끠"))
        }
        feedList.apply {
            add(FeedListModel(arrayListOf(FeedImgModel(R.drawable.cat1),
                FeedImgModel(R.drawable.cat2)
            ),R.drawable.dog1, "posite","고양이","건끠", "귀여워요!!"))
            add(FeedListModel(arrayListOf(FeedImgModel(R.drawable.cat1),
                FeedImgModel(R.drawable.cat2)
            ),R.drawable.dog2, "comst","고양이",null, null))
        }
        binding.reelsRecycler.adapter = ReelsAdapter(reelsList, requireContext())
        binding.reelsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.feedRecycler.adapter = FeedAdapter(feedList,requireContext())
        binding.feedRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

}