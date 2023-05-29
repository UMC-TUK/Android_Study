package com.example.chapter9_exercise

/*
    본 저작물은 '질병관리청', '기상청', '한국환경공단'에서 'OO년'작성하여 공공누리 제1유형으로 각각
    '질병관리청_코로나19 국내발생현황 조회', '한국환경공단_에어코리아_미세먼지 경보 발령 현황', '기상청_단기예보 ((구)_동네예보) 조회서비스'를
     이용하였습니다.
*/
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.chapter9_exercise.api.ApiRepository
import com.example.chapter9_exercise.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pageW = 1
    private var pageA = 1
    private var pageC = 1
    private var countW = 0
    private var countA = 0
    private var countC = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apiButton.setOnClickListener {
            shortWeather()
            airCondition()
            coronaState()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun shortWeather() {
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val now = currentDate()
            val result = coroutine.async(Dispatchers.IO){
                ApiRepository().shortWeather(pageW, now[0], now[1])
            }.await()
            if(result != null ) {
                pageW++
                Log.d("realResult", "weather: $result")
                countW = 0
            } else {
                if(countW <3) {
                    countW++
                    shortWeather()
                }
            }
        }
    }

    private fun airCondition() {
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val result = coroutine.async(Dispatchers.IO){
                ApiRepository().airCondition(pageW)
            }.await()
            if(result != null ) {
                pageA++
                Log.d("realResult", "air: ${result.response.body.items}")
                countA = 0
            } else {
                if(countA <3) {
                    countA++
                    airCondition()
                }
            }
        }
    }

    private fun coronaState() {
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val result = coroutine.async(Dispatchers.IO){
                ApiRepository().coronaState()
            }.await()
            if(result != null ) {
                pageC++
                Log.d("realResult", "corona: ${result.response.result}")
                countC = 0
            } else {
                if(countC <3) {
                    countC++
                    coronaState()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun currentDate(): List<String> {
        val current = LocalDateTime.now()
        val formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd")
        val formatter2 = DateTimeFormatter.ofPattern("HHmm")
        val formatDate = current.format(formatter1)
        val formatTime = current.format(formatter2)
        return listOf(formatDate, formatTime)
    }
}