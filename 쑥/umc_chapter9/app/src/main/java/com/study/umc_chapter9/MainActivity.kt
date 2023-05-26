package com.study.umc_chapter9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.umc_chapter9.adapter.Adapter
import com.study.umc_chapter9.databinding.ActivityMainBinding
import com.study.umc_chapter9.retrofit.RetrofitClient
import com.study.umc_chapter9.retrofit.data.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var call : Call<DataModel>
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: Adapter
    lateinit var data : MutableList<DataModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = mutableListOf()
        adapter = Adapter(data)
        binding.recyclerView.adapter = adapter
        binding.random.setOnClickListener {

            val random = Random.nextInt(500)
            call = RetrofitClient.getApiService()!!.test_api_get(random.toString())
            Toast.makeText(this, random.toString(), Toast.LENGTH_SHORT).show()

            call.enqueue(object : Callback<DataModel> {
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    var result : DataModel? = response.body()
                    if(result == null){
                        Toast.makeText(this@MainActivity, "오류 : ${response.code()}", Toast.LENGTH_SHORT).show()
                    }else{
                        data.add(result)
                        adapter.notifyItemInserted(data.size)
                    }

                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "실패", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}