package com.example.umc_chatper7_mission2

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_chatper7_mission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer
    lateinit var thread: Thread
    lateinit var handler: Handler
    var pauseCheck = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                when(msg.what){
                    1 ->{

                    }
                    -1 -> {

                    }
                    0 ->{
                        binding.seekBar.progress = mediaPlayer.currentPosition
                        val m = binding.seekBar.progress / 60000
                        val s = binding.seekBar.progress % 60000 / 1000
                        val strTime = String.format("%02d:%02d", m, s)
                        binding.time.text = strTime
                    }
                }
            }
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.the_mumbai_beat)       //안드로이드 스튜디오 도플라밍고부터 각 모듈별로 R이 구분되어 import를 변경해야 한다.
        binding.seekBar.max = mediaPlayer.duration

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
                val m = progress / 60000
                val s = progress % 60000 / 1000
                val strTime = String.format("%02d:%02d", m, s)
                binding.time.text = strTime
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.start.setOnClickListener {
            mediaPlayer.seekTo(0)
        }

        binding.play.setOnClickListener {
            if(pauseCheck){
                mediaPlayer.pause()
                pauseCheck = false
            }else{
                mediaPlayer.start()
                musicThread()
                pauseCheck = true
            }
        }

        binding.stop.setOnClickListener {
            mediaPlayer.pause()
            pauseCheck = false
            mediaPlayer.seekTo(0)
        }

        binding.end.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.duration)
        }
    }

    fun musicThread() {
        val task = Runnable {
            while (mediaPlayer.isPlaying()) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }
                binding.seekBar.progress = mediaPlayer.currentPosition
            }
        }
        thread = Thread(task)
        thread.start()
    }
}