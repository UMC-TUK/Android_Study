package com.example.chapter10

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.chapter10.databinding.ActivityServiceBinding
import com.kakao.sdk.user.model.User

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra("id")?.let {
            binding.userId.text = it
        }
        intent.getStringExtra("url")?.let {
            binding.userAddress.text = it
        }
        intent.getStringExtra("profile")?.let {
            Glide.with(binding.profileImg).load(it)
                .into(binding.profileImg)
        }
        intent.getParcelableExtra("kakao", User::class.java)?.let {
            binding.userId.text = it.kakaoAccount?.profile?.nickname
            binding.userAddress.text = it.kakaoAccount?.email
            Glide.with(binding.profileImg).load(it.kakaoAccount?.profile?.thumbnailImageUrl)
                .into(binding.profileImg)
        }

    }
}