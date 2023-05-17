package com.example.chapter7

import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var timer: CountDownTimer
    private var forward = 0
    private var backward = 30
    private var bar = 0
    private var forwardTimer = ForwardTimer()
    private var backwardTimer = BackwardTimer()
    private var seekTimer = SeekTime()
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
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.timerStart.setOnClickListener {
            start()
        }

        binding.timerStop.setOnClickListener {
            stop()
        }

        binding.timerReset.setOnClickListener {
            reset()
        }
    }
    private fun start() {
        stop()
        forwardTimer = ForwardTimer()
        backwardTimer = BackwardTimer()
        seekTimer = SeekTime()
        forwardTimer.start()
        backwardTimer.start()
        seekTimer.start()
    }

    private fun stop() {
        forwardTimer.interrupt()
        backwardTimer.interrupt()
        seekTimer.interrupt()
    }

    private fun reset() {
        stop()
        forwardTimer = ForwardTimer()
        backwardTimer = BackwardTimer()
        seekTimer = SeekTime()
        forward = 0
        backward = 30
        bar = 0
        binding.forwardTimer.text = "0"
        binding.backwardTimer.text = "30"
        binding.seekTime.progress = bar
        binding.currentSeek.text = bar.toString()
    }

    inner class ForwardTimer: Thread() {
        override fun run() {
            super.run()
            while(true) {
                try {
                    handler.post{
                        binding.forwardTimer.text = forward.toString()
                    }
                    sleep(1000)
                    if(forward < 30){
                        forward++
                    } else {
                        forward = 0
                    }
                } catch (e: InterruptedException) {
                    break
                }
            }
        }
    }

    inner class BackwardTimer: Thread() {
        override fun run() {
            super.run()
            while(true) {
                try {
                    handler.post{
                        binding.backwardTimer.text = backward.toString()
                    }
                    sleep(3000)
                    if(backward <= 0){
                        backward = 30
                    } else {
                        backward--
                    }
                } catch (e: InterruptedException) {
                    break
                }
            }
        }
    }

    inner class SeekTime: Thread() {
        override fun run() {
            super.run()
            while(true) {
                try{
                    handler.post{
                        binding.seekTime.progress = bar
                        binding.currentSeek.text = bar.toString()
                    }
                    val message = handler.obtainMessage()
                    message.arg1 = bar
                    handler.sendMessage(message)
                    sleep(5000)
                    if(bar < 40) {
                        bar++
                    } else {
                        bar = 0
                    }
                } catch (e: InterruptedException) {
                    break
                }
            }
        }
    }
}