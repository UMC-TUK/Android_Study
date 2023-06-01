package com.example.ch10

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.example.ch10.databinding.ActivityMainBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    var mGoogleSignInClient: GoogleSignInClient? = null
    var googleLoginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == -1) {
                val data = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                getGoogleInfo(task)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.googleLogin.setOnClickListener {
            intent = mGoogleSignInClient!!.signInIntent
            googleLoginLauncher.launch(intent)

            if (mGoogleSignInClient != null) {
                binding.googleLogin.isGone = true
            }

        }


    }

    fun getGoogleInfo(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            //binding.name.text = account.familyName + "/" + account.givenName
            //binding.mail.text = account.email
            Glide.with(this)
                .load(account.photoUrl)
                .circleCrop()
                .into(binding.image)

            binding.info.text = account.familyName + "/" + account.givenName +"\n "+
                    account.email
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

}