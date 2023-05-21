package com.comst19.chapter07mission

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.comst19.chapter07mission.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

// https://pgtd.tistory.com/246

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding : ActivityMainBinding
    private var mediaPlayer : MediaPlayer? = null
    private var mp3List = mutableListOf<Int>(R.raw.music1,R.raw.music2, R.raw.music3, R.raw.music4)
    private var selectMusic = 0
    private lateinit var adapter : RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.palyBtn.setOnClickListener{
            playMusic(mp3List[selectMusic])
            Thread{
                var timeFormat = SimpleDateFormat("mm:ss")
                if(mediaPlayer == null) return@Thread
                binding.seekbar.max = mediaPlayer!!.duration
                while (mediaPlayer!!.isPlaying){
                    if (mediaPlayer == null) break
                    runOnUiThread{
                        binding.seekbar.progress = mediaPlayer!!.currentPosition
                        binding.musicTime.text = timeFormat.format(mediaPlayer!!.currentPosition)
                    }
                    Thread.sleep(1000)
                }
            }.start()
        }
        binding.pauseBtn.setOnClickListener{ pauseMusic() }
        binding.stopBtn.setOnClickListener{ stopMusic() }

        adapter = RVAdapter(mp3List, this)
        binding.RV.adapter = adapter
        binding.RV.layoutManager = LinearLayoutManager(this)

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    fun playMusic(music : Int){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, music)
            mediaPlayer?.start()
        }else{
            mediaPlayer = MediaPlayer.create(this, music)
            mediaPlayer?.start()
        }
    }

    fun pauseMusic(){
        mediaPlayer?.pause()
    }

    fun stopMusic(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onClick(position: Int) {
        binding.musicName.text = "Music $position"
        binding.musicTime.text = "00:00"
        selectMusic = position
        binding.seekbar.progress = 0
    }

    override fun onDestroy() {
        mediaPlayer = null
        super.onDestroy()
    }

}