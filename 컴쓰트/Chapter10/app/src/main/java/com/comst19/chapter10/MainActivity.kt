package com.comst19.chapter10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.comst19.chapter10.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var mGoogleSignClient : GoogleSignInClient
    lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        account?.let {
            Toast.makeText(this, "로그인 되어있습니다.", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "로그아웃 되어있습니다.",Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignClient = GoogleSignIn.getClient(this, gso)

        binding.signIn.setOnClickListener {
            signIn()
        }

        binding.signOut.setOnClickListener {
            signOut()
        }

        binding.getProfile.setOnClickListener {
            GetCurrentUserProfile()
        }

    }

    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(
        )) { result ->
            if( result.resultCode == Activity.RESULT_OK){
                val task : Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val familyName = account?.familyName.toString()
            val givenName = account?.givenName.toString()
            val displayName = account?.displayName.toString()
            val photoUrl = account?.photoUrl.toString()
        }catch (e : ApiException){

        }
    }

    private fun signIn() {
        val signInIntent : Intent = mGoogleSignClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun signOut() {
        mGoogleSignClient.signOut()
            .addOnCompleteListener(this) {

            }
    }

    private fun revokeAccess() {
        mGoogleSignClient.revokeAccess()
            .addOnCompleteListener(this){

            }
    }

    private fun GetCurrentUserProfile() {
        val curUser = GoogleSignIn.getLastSignedInAccount(this)
        curUser?.let {
            val email = curUser.email.toString()
            val familyName = curUser.familyName.toString()
            val givenName = curUser.givenName.toString()
            val displayName = curUser.displayName.toString()
            val photoUrl = curUser.photoUrl.toString()

            binding.profileTV.text = " email : $email \n 이름 : ${familyName+givenName}"
        }
    }
}