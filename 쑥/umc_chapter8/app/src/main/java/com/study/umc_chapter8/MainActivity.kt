package com.study.umc_chapter8

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.study.umc_chapter8.database.AppDatabase
import com.study.umc_chapter8.databinding.ActivityMainBinding

lateinit var db : AppDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var sharedPreference : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = getSharedPreferences("signFile", MODE_PRIVATE)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "DictionaryDB").build()

        if(sharedPreference.getBoolean("signIn", false)){
            val intent = Intent(this, RambleActivity::class.java)
            startActivity(intent)
        }


        binding.signUp.setOnClickListener {
            Toast.makeText(this, "Sing Up\nemail : ${binding.email.text}\npassword : ${binding.password.text}", Toast.LENGTH_SHORT).show()
            val editor: SharedPreferences.Editor = sharedPreference.edit()

            editor.putBoolean("signIn", true)
            editor.commit()

            val intent = Intent(this, RambleActivity::class.java)
            startActivity(intent)
        }

        binding.signIn.setOnClickListener {
            Toast.makeText(this, "Sing In\nemail : ${binding.email.text}\npassword : ${binding.password.text}", Toast.LENGTH_SHORT).show()
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putBoolean("signIn", true)
            editor.commit()

            val intent = Intent(this, RambleActivity::class.java)
            startActivity(intent)
        }
    }
}