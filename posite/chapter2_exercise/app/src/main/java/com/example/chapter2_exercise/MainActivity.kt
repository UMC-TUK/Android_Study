package com.example.chapter2_exercise

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.chapter2_exercise.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewmodel = Viewmodel()
    private lateinit var mainFrag: MainFragment
    private lateinit var searchFrag: SearchFragment
    private lateinit var profileFrag: ProfileFragment
    private lateinit var toolbarMenu: Menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.instaViewmodel = viewmodel

        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Instagram"

        mainFrag = MainFragment()
        searchFrag = SearchFragment()
        profileFrag = ProfileFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.change_frame, mainFrag)
            .add(R.id.change_frame, searchFrag)
            .add(R.id.change_frame, profileFrag)
            .hide(profileFrag)
            .hide(searchFrag)
            .commit()


        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.insta_home -> {
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    supportActionBar?.title = "Instagram"
                    binding.toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText)
                    toolbarMenu.clear()
                    menuInflater.inflate(R.menu.toolbar_menu, binding.toolbar.menu)
                    binding.searchContent.visibility = View.GONE
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(mainFrag)
                        .hide(profileFrag)
                        .hide(searchFrag)
                        .commit()
                }
                R.id.insta_search -> {
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                    toolbarMenu.clear()
                    menuInflater.inflate(R.menu.search_menu, binding.toolbar.menu)
                    binding.searchContent.visibility = View.VISIBLE
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(searchFrag)
                        .hide(profileFrag)
                        .hide(mainFrag)
                        .commit()
                }
                R.id.insta_post -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 새 게시물 추가하기 눌림!!", Toast.LENGTH_SHORT).show()
                }
                R.id.insta_reels -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 릴스들 눌림!!", Toast.LENGTH_SHORT).show()
                }
                R.id.insta_account -> {
                    Toast.makeText(applicationContext, "바텀네비게이션뷰의 내 계정 눌림!!", Toast.LENGTH_SHORT).show()
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    binding.toolbar.setTitleTextAppearance(this, R.style.Toolbar_Normal)
                    toolbarMenu.clear()
                    menuInflater.inflate(R.menu.profile_menu, binding.toolbar.menu)
                    binding.searchContent.visibility = View.GONE
                    supportActionBar?.title = "posite8481"
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(profileFrag)
                        .hide(searchFrag)
                        .hide(mainFrag)
                        .commit()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        toolbarMenu = menu
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
            R.id.insta_search_place -> {
                Toast.makeText(this, "장소검색 버튼 눌림!!", Toast.LENGTH_SHORT).show()
            }
            R.id.profile_make -> {
                Toast.makeText(this, "만들기 버튼 눌림!!", Toast.LENGTH_SHORT).show()
            }
            R.id.profile_menu -> {
                Toast.makeText(this, "메뉴 버튼 눌림!!", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            for(fragment: Fragment in supportFragmentManager.fragments) {
                if(!fragment.isHidden && fragment != mainFrag) {
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                    supportActionBar?.title = "Instagram"
                    toolbarMenu.setGroupVisible(R.id.toolbar_group, true)
                    menuInflater.inflate(R.menu.toolbar_menu, binding.toolbar.menu)
                    binding.searchContent.visibility = View.GONE
                    val transactionN = supportFragmentManager.beginTransaction()
                    transactionN.show(mainFrag)
                        .hide(searchFrag)
                        .hide(profileFrag)
                        .commit()
                    binding.bottomNavigation.selectedItemId = R.id.insta_home
                    Toast.makeText(applicationContext, "뒤로가기 눌림!!", Toast.LENGTH_SHORT).show()
                    break
                }
                if(!fragment.isHidden && fragment == mainFrag) {
                    Toast.makeText(applicationContext, "종료!!", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                }
            }
        }
    }
}