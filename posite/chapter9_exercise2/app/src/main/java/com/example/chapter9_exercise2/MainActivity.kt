package com.example.chapter9_exercise2

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chapter9_exercise2.api.APIRepository
import com.example.chapter9_exercise2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var code = ""
    private var accessToken = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.goAuth.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${BuildConfig.baseUrl}authorize?client_id=${BuildConfig.id}"))
            startActivity(intent)
        }

        binding.finishAuth.setOnClickListener {
            if(code.isNotBlank()) {
                accessToken()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val new =  intent?.data?.getQueryParameter("code")
        Log.d("code", "code = $new")
        if(!new.isNullOrEmpty()) {
            Log.d("code", "code = $new")
            code = new
            binding.goAuth.isEnabled = false
        }
    }


    private fun accessToken() {
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val result = coroutine.async(Dispatchers.IO) {
                APIRepository().getAccessToken(code)
            }.await()
            if(result != null) {
                Log.d("result", "access: ${result.access_token}")
                result.access_token?.let{
                    accessToken = it
                    userInfo()
                }
            }
        }
    }

    private fun userInfo() {
        val coroutine = CoroutineScope(Dispatchers.Main)
        coroutine.launch {
            val result = coroutine.async(Dispatchers.IO) {
                APIRepository().getUserInfo(accessToken)
            }.await()
            if(result != null) {
                Log.d("result", "id: ${result.id},  email: ${result.email}")
                val intent = Intent(applicationContext, ServiceActivity::class.java)
                intent.putExtra("id", result.name)
                intent.putExtra("url", result.html_url)
                startActivity(intent)
            }
        }
    }
}