package com.example.chapter7_exercise

import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import com.example.chapter7_exercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var forward = 0
    private lateinit var forwardTimer: ForwardTimer
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var mediaThread: MediaThread
    private val handler =  object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            try {
                Log.d("message", msg.arg1.toString())
                Toast.makeText(applicationContext, msg.arg1.toString(), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        forwardTimer = ForwardTimer(applicationContext)
        mediaThread = MediaThread()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.sad_piano_background_music)
        binding.musicSeek.max = mediaPlayer.duration
        binding.timerStart.setOnClickListener {
            start()
        }
        binding.timerStop.setOnClickListener {
            stop()
        }
        binding.timerReset.setOnClickListener {
            reset()
        }

        binding.musicStartPause.setOnClickListener{
            if(mediaPlayer.isPlaying) {
                musicPause()
            } else {
                musicStart()
            }
        }

        binding.musicStop.setOnClickListener {
            musicStop()
        }
        binding.musicSeek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress)
                }
                val minute = progress /60000
                val second = (progress % 60000) / 1000
                val strTime = String.format("%02d:%02d", minute, second)
                binding.currentMusicTime.text = strTime
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
    private fun start() {
        stop()
        forwardTimer = ForwardTimer(applicationContext)
        forwardTimer.start()
    }

    private fun stop() {
        forwardTimer.interrupt()
    }

    private fun reset() {
        stop()
        forwardTimer = ForwardTimer(applicationContext)
        forward = 0
        binding.forwardTimer.text = "0"
    }

    private fun musicStart() {
        binding.musicStartPause.setBackgroundResource(R.drawable.video_pause_button)
        mediaPlayer.seekTo(binding.musicSeek.progress)
        mediaPlayer.start()
        mediaThread = MediaThread()
        mediaThread.start()
    }

    private fun musicPause() {
        binding.musicStartPause.setBackgroundResource(R.drawable.play_button)
        mediaPlayer.pause()
    }

    private fun musicStop() {
        binding.musicStartPause.setBackgroundResource(R.drawable.play_button)
        mediaPlayer.seekTo(0)
        mediaPlayer.stop()
        mediaPlayer.prepare()
        mediaThread.interrupt()
    }

    inner class ForwardTimer(private val context: Context): Thread() {
        private val soundPool: SoundPool = SoundPool.Builder().build()
        private val soundId = soundPool.load(context,R.raw.alarm, 1)
        override fun run() {
            super.run()
            while(true) {
                try {
                    handler.post{
                        binding.forwardTimer.text = forward.toString()
                    }
                    if(forward < 30){
                        forward++
                    } else {
                        forward = 0
                        Log.d("current", binding.forwardTimer.text.toString())
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    }
                    sleep(1000)
                } catch (e: InterruptedException) {
                    break
                }
            }
        }
    }

    inner class MediaThread: Thread() {
        override fun run() {
            super.run()
            while(true) {
                try{
                    binding.musicSeek.progress = mediaPlayer.currentPosition
                } catch (e: InterruptedException) {
                    break
                }
            }

        }
    }
}