package com.study.umc_chapter7_mission

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock.sleep
import android.view.View
import android.widget.Toast
import com.study.umc_chapter7_mission.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var thread : Thread
    lateinit var handler : Handler
    lateinit var soundPool: SoundPool
    private lateinit var audioAttributes : AudioAttributes
    var sid by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
             audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            soundPool = SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build()
        }else{
            soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }

        sid = soundPool.load(this, R.raw.arlam, 1)

        @SuppressLint("HandlerLeak")
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when(msg.what){
                    -2 ->{
                        binding.time.visibility = View.VISIBLE
                        binding.timer.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "취소", Toast.LENGTH_SHORT).show()
                    }
                    -1 -> {
                        Toast.makeText(applicationContext, "알람", Toast.LENGTH_SHORT).show()
                        playOrderAlarm()
                        binding.time.visibility = View.VISIBLE
                        binding.timer.visibility = View.INVISIBLE
                    }
                    else ->{
                        var hour = msg.what/3600
                        var minute = (msg.what - hour*3600)/60
                        var second = (msg.what - hour*3600 - minute*60)
                        val time = "$hour:$minute:$second"
                        binding.timer.text = time
                    }
                }
            }
        }

        binding.start.setOnClickListener {
                binding.timer.text = binding.time.text.toString()
                timeThreadCreate(binding.time.text.toString())
                binding.time.visibility = View.INVISIBLE
                binding.timer.visibility = View.VISIBLE
        }

        binding.cancle.setOnClickListener {
            thread.interrupt()
            binding.time.visibility = View.VISIBLE
            binding.timer.visibility = View.INVISIBLE
        }
    }

    private fun timeThreadCreate(time : String)  {
        var times = time.split(":")
        var hour = times[0].toInt()
        var minute = times[1].toInt()
        var second = times[2].toInt()
        var total = hour*3600 + minute*60 + second
        thread = thread(true){
            while(true) {
                if(thread.isInterrupted){
                    handler.sendEmptyMessage(1)
                    break
                }

                sleep(1000)
                total--
                if (total == 0) {
                    handler.sendEmptyMessage(-1)
                    break
                }

                handler.sendEmptyMessage(total)
            }

        }
    }

    fun playOrderAlarm()  {
        soundPool.play(sid, 1F, 1F,0,0, 1F)
    }

    override fun onStop() {
        soundPool.release()
        super.onStop()
    }
}