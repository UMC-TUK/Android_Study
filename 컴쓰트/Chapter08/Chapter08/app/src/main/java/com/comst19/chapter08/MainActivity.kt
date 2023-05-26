package com.comst19.chapter08

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.comst19.chapter08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(sharedPreferences.getBoolean("FLAG", false)){
            binding.idEdit.setText(sharedPreferences.getString("ID",""))
            binding.pwdEdit.setText(sharedPreferences.getString("PWD",""))
            binding.autoLogin.isChecked = true
        }

        binding.loginBtn.setOnClickListener {
            if(binding.autoLogin.isChecked){
                editor.putString("ID", binding.idEdit.text.toString())
                editor.putString("PWD", binding.pwdEdit.text.toString())
                editor.putBoolean("FLAG",true)
                editor.commit()
            }else{
                editor.remove("ID")
                editor.remove("PWD")
                editor.remove("FLAG")
                editor.apply()
            }
            val intent = Intent(this, RoomDBActivity::class.java)
            startActivity(intent)
        }


    }
}