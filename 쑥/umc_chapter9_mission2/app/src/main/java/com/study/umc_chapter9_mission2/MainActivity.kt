package com.study.umc_chapter9_mission2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.study.umc_chapter9_mission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this@MainActivity, BuildConfig.NATIVE_APP_KEY)

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
            else if (token != null) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            }
        }

        with(binding){
            signIn.setOnClickListener {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@MainActivity)) {
                    UserApiClient.instance.loginWithKakaoTalk(this@MainActivity) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "카카오톡으로 로그인 실패", error)

                            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }

                            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                            UserApiClient.instance.loginWithKakaoAccount(
                                this@MainActivity,
                                callback = callback
                            )
                        } else if (token != null) {
                            Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                            UserApiClient.instance.me { user, _ ->
                                val email = user?.kakaoAccount?.email
                                val name = user?.kakaoAccount?.profile?.nickname

                                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                intent.putExtra("email", email)
                                intent.putExtra("name", name)
                                startActivity(intent)
                            }
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this@MainActivity, callback = callback)
                }
            }
            
            signOut.setOnClickListener {
                UserApiClient.instance.unlink { error ->
                    if (error != null) {
                        Log.e(TAG, "연결 끊기 실패", error)
                    }
                    else {
                        Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                    }
                }
            }
        }
    }
}