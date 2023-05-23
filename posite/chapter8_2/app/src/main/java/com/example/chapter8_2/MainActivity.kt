package com.example.chapter8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter8_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var musicDB: MusicDB? = null
    private val musics = arrayListOf<MusicEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.delete)

        binding.searchIcon.setOnClickListener {
            var searchResult: MusicEntity? = null
            val coroutine = CoroutineScope(Dispatchers.Main)
            if(binding.inputText.text.isNotEmpty()) {
                coroutine.launch {
                    searchResult = coroutine.async(Dispatchers.IO) { musicDB?.MusicDao()?.getMusic(binding.inputText.text.toString()) }.await()
                    if(searchResult != null) {
                        binding.musicList.adapter = MusicListAdapter(listOf(searchResult!!), applicationContext)
                        binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                        binding.musicList.adapter?.notifyDataSetChanged()
                    }
                }
            } else {
                var musicList: List<MusicEntity>? = null
                coroutine.launch {
                    coroutine.async(Dispatchers.IO){
                        musicList = musicDB?.MusicDao()?.getMusics()
                    }.await()
                    if(musicList != null) {
                        binding.musicList.adapter =  MusicListAdapter(musicList!!, applicationContext)
                        binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                        binding.musicList.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

        binding.inputText.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                var searchResult: MusicEntity? = null
                val coroutine = CoroutineScope(Dispatchers.Main)
                if(binding.inputText.text.isNotEmpty()) {
                    coroutine.launch {
                        searchResult = coroutine.async(Dispatchers.IO) { musicDB?.MusicDao()?.getMusic(binding.inputText.text.toString()) }.await()
                        if(searchResult != null) {
                            binding.musicList.adapter = MusicListAdapter(listOf(searchResult!!), applicationContext)
                            binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                            binding.musicList.adapter?.notifyDataSetChanged()
                        }
                    }
                } else {
                    var musicList: List<MusicEntity>? = null
                    coroutine.launch {
                        coroutine.async(Dispatchers.IO){
                            musicList = musicDB?.MusicDao()?.getMusics()
                        }.await()
                        if(musicList != null) {
                            binding.musicList.adapter =  MusicListAdapter(musicList!!, applicationContext)
                            binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                            binding.musicList.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
            true
        }


        musics.apply {
            add(MusicEntity("그라데이션", "10cm"))
            add(MusicEntity("쓰담쓰담", "10cm"))
            add(MusicEntity("폰서트", "10cm"))
            add(MusicEntity("애상", "10cm"))
            add(MusicEntity("pet", "10cm"))
            add(MusicEntity("꽃", "지수"))
            add(MusicEntity("사건의 지평선", "윤하"))
            add(MusicEntity("Ditto", "NewJeans"))
            add(MusicEntity("Love of My Life", "Queen"))
        }
        try{
            musicDB = MusicDB.getInstance(applicationContext)
            CoroutineScope(Dispatchers.IO).launch {
                musics.forEach {
                    val change = musicDB?.MusicDao()?.getMusic(it.name)
                    if(change ==null)
                        musicDB?.MusicDao()?.insert(it)
                    else {
                        if(change.artist == "blank") {
                            musicDB?.MusicDao()?.update(it)
                        }
                    }
                }
                binding.musicList.adapter = musicDB?.MusicDao()?.let {
                    MusicListAdapter(it.getMusics(), applicationContext)}
                binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                binding.musicList.adapter?.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, binding.toolbar.menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                if(binding.inputText.text.isNotEmpty()) {
                    var musicList: List<MusicEntity>? = null
                    val coroutine = CoroutineScope(Dispatchers.Main)
                    coroutine.launch {
                        val result = coroutine.async(Dispatchers.IO) { musicDB?.MusicDao()?.getMusic(binding.inputText.text.toString()) }.await()
                        if(result != null) {
                            coroutine.async(Dispatchers.IO) {
                                musicDB?.MusicDao()?.delete(MusicEntity(binding.inputText.text.toString(), "blank"))
                                musicList = musicDB?.MusicDao()?.getMusics()}
                                .await()
                            binding.musicList.adapter =  MusicListAdapter(musicList!!, applicationContext)
                            binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                            binding.musicList.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
            R.id.add_song->{
                if(binding.inputText.text.isNotEmpty()) {
                    var musicList: List<MusicEntity>? = null
                    val coroutine = CoroutineScope(Dispatchers.Main)
                    coroutine.launch {
                        val result = coroutine.async(Dispatchers.IO) { musicDB?.MusicDao()?.getMusic(binding.inputText.text.toString()) }.await()
                        if(result == null) {
                            coroutine.async(Dispatchers.IO) {
                                val remained = musics.filter { it.name == binding.inputText.text.toString() }
                                if(remained.isNotEmpty()) {
                                    musicDB?.MusicDao()?.insert(remained[0])
                                } else {
                                    musicDB?.MusicDao()?.insert(MusicEntity(binding.inputText.text.toString(), "blank"))
                                }
                                musicList = musicDB?.MusicDao()?.getMusics()}
                                .await()

                            binding.musicList.adapter =  MusicListAdapter(musicList!!, applicationContext)
                            binding.musicList.layoutManager = LinearLayoutManager(applicationContext)
                            binding.musicList.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}