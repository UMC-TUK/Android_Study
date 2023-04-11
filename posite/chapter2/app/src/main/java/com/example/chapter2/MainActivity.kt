package com.example.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewmodel = Viewmodel()
    private var reelsList = ArrayList<ReelsModel>()
    private var feedList = ArrayList<FeedListModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.instaViewmodel = viewmodel

        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Instagram"

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
        binding.reelsRecycler.adapter = ReelsAdapter(reelsList, this)
        binding.reelsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.feedRecycler.adapter = FeedAdapter(feedList,this)
        binding.feedRecycler.layoutManager = LinearLayoutManager(this)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.insta_home -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 홈 눌림!!", Toast.LENGTH_SHORT).show()
                }
                R.id.insta_search -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 검색 눌림!!", Toast.LENGTH_SHORT).show()
                }
                R.id.insta_post -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 새 게시물 추가하기 눌림!!", Toast.LENGTH_SHORT).show()
                }
                R.id.insta_reels -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 릴스들 눌림!!", Toast.LENGTH_SHORT).show()
                }
                R.id.insta_account -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 내 계정 눌림!!", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, binding.toolbar.menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.insta_favorite -> {
                Toast.makeText(this, "알림 버튼 눌림!!", Toast.LENGTH_SHORT).show()
            }

            R.id.insta_send -> {
                Toast.makeText(this, "메시지 버튼 눌림!!", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}