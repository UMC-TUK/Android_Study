package com.example.chapter10

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.chapter10.api.APIRepository
import com.example.chapter10.databinding.ActivityMainBinding
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
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
        var keyHash = Utility.getKeyHash(this)
        KakaoSdk.init(this, "8eb469370f02128498f483cfd0819d18")

        Log.d("hash", "keyHash: $keyHash")
        setContentView(binding.root)
        binding.buttonKakaoLogin.setOnClickListener {
            lifecycleScope.launch {
                var kakaoUser: User? = null
                try {
                    // 서비스 코드에서는 간단하게 로그인 요청하고 oAuthToken 을 받아올 수 있다.
                    val oAuthToken = UserApiClient.loginWithKakao(applicationContext)
                    Log.d("MainActivity", "beanbean > $oAuthToken")
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        if (error != null) {
                            Log.e("user", "토큰 정보 보기 실패", error)
                        }
                        else if (tokenInfo != null) {
                            Log.i("user", "토큰 정보 보기 성공" +
                                    "\n회원번호: ${tokenInfo.id}" +
                                    "\n만료시간: ${tokenInfo.expiresIn} 초")
                        }
                    }
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e("user", "사용자 정보 요청 실패", error)
                        }
                        else if (user != null) {
                            kakaoUser = user
                            Log.i("user", "사용자 정보 요청 성공" +
                                    "\n회원번호: ${user.id}" +
                                    "\n이메일: ${user.kakaoAccount?.email}" +
                                    "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                    "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                            val intent = Intent(applicationContext, ServiceActivity::class.java)
                            intent.putExtra("kakao",kakaoUser)
                            startActivity(intent)
                        }
                    }
                } catch (error: Throwable) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        Log.d("MainActivity", "${error.message} 사용자가 명시적으로 취소")
                    } else {
                        Log.e("MainActivity", "인증 에러 발생", error)
                    }
                }
            }
        }
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
                Log.d("result", "url: ${result.avatar_url}")
                val intent = Intent(applicationContext, ServiceActivity::class.java)
                intent.putExtra("id", result.name)
                intent.putExtra("url", result.html_url)
                intent.putExtra("profile", result.avatar_url)
                startActivity(intent)
            }
        }
    }
}