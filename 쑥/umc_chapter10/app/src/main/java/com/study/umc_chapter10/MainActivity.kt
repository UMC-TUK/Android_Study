package com.study.umc_chapter10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.study.umc_chapter10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var mGoogleSignInClient : GoogleSignInClient
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        with(binding){
            signIn.setOnClickListener {
                signIn()
            }
            signOut.setOnClickListener {
                signOut()
            }
        }
    }

    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleSignResult(task)
            }
        }
    }

    private fun handleSignResult(task: Task<GoogleSignInAccount>){
        try {
            val account = task.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val photoUrl = account?.photoUrl.toString()

            Toast.makeText(this, "email : $email", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "photoUrl : $photoUrl", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("email", account?.email)
            intent.putExtra("photoUrl", account?.photoUrl)
            startActivity(intent)

        }catch (e: ApiException){
            Log.w("failed", "sign In Result:failed code = " + e.statusCode)
        }
    }

    private fun signIn(){
        val signIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signIntent)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this){
            Toast.makeText(this, "Sign Out!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        account?.let {
            Toast.makeText(this, "Logged In\nemail : ${account.email}\nphotoUrl : ${account.photoUrl}", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "Not Yet", Toast.LENGTH_SHORT).show()
    }
}