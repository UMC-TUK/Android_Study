package com.study.umc_chapter9_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.study.umc_chapter9_mission.adapter.RecyclerAdapter
import com.study.umc_chapter9_mission.data.RecyclerItem
import com.study.umc_chapter9_mission.databinding.ActivityMainBinding
import com.study.umc_chapter9_mission.retrofit.DustRetrofit
import com.study.umc_chapter9_mission.retrofit.EqkRetrofit
import com.study.umc_chapter9_mission.retrofit.TyphooRetrofit
import com.study.umc_chapter9_mission.retrofit.data.Dust
import com.study.umc_chapter9_mission.retrofit.data.Eqk
import com.study.umc_chapter9_mission.retrofit.data.Typhoo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var callDust : Call<Dust>
    private lateinit var callEqk : Call<Eqk>
    private lateinit var callTyphoo : Call<Typhoo>
    private lateinit var adapter : RecyclerAdapter
    private lateinit var toDate : String
    private lateinit var fromDate : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toDate = setToDate()
        fromDate = setFromDate()

        callDust = DustRetrofit.getApiService()!!.getApi(year = "2023")
        callEqk = EqkRetrofit.getApiService()!!.getApi(fromTmFc = fromDate, toTmFc = toDate)
        callTyphoo = TyphooRetrofit.getApiService()!!.getApi(fromTmFc = fromDate, toTmFc = toDate)

        var recyclerItem = mutableListOf<RecyclerItem>()
        adapter = RecyclerAdapter(recyclerItem)
        binding.recyclerView.adapter = adapter


        val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("HTTP", message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(loggingInterceptor)

        with(binding){
            dust.setOnClickListener {
                callDust.enqueue(object : Callback<Dust> {
                    override fun onResponse(call: Call<Dust>, response: Response<Dust>) {
                        if (response.isSuccessful) {
                            val result: Dust? = response.body()
                            if (result != null) {
                                val data = result.response.body.items
                                recyclerItem = mutableListOf()
                                for (item in data) {
                                    val districtName = item.districtName
                                    val dataDate = item.dataDate
                                    val issueGbn = item.issueGbn
                                    val moveName = item.moveName
                                    Log.d("Dust API Success" ,"$districtName $dataDate $issueGbn $moveName")
                                    recyclerItem.add(RecyclerItem(districtName, moveName, issueGbn))
                                }
                                runOnUiThread {
                                    adapter = RecyclerAdapter(recyclerItem)
                                    binding.recyclerView.adapter = adapter
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "오류: ${response.code()}", Toast.LENGTH_SHORT).show()
                            Log.d("Dust API test", "오류: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<Dust>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "실패 ${t.printStackTrace()}", Toast.LENGTH_SHORT).show()
                        Log.d("Dust API test", "실패 : ${t.printStackTrace()}")

                        val errorMessage = t.message ?: "Unknown error occurred"
                        Toast.makeText(this@MainActivity, "실패: $errorMessage", Toast.LENGTH_SHORT).show()
                        Log.d("Dust API test", "실패: $errorMessage")
                    }

                })

            }
            eqk.setOnClickListener {
                callEqk.enqueue(object : Callback<Eqk>{
                    override fun onResponse(call: Call<Eqk>, response: Response<Eqk>) {
                        if (response.isSuccessful) {
                            val result: Eqk? = response.body()
                            if (result != null) {
                                recyclerItem = mutableListOf()
                                if(result.response.header.resultMsg == "NO_DATA"){
                                    recyclerItem.add(RecyclerItem("NO_DATA", "NO_DATA","NO_DATA"))

                                }else{
                                    val data = result.response.body.items.item
                                    for (item in data) {
                                        val cnt = item.cnt
                                        val loc = item.loc
                                        val mt = item.mt
                                        val tmEqk = item.tmEqk
                                        Log.d(
                                            "Eqk API Success",
                                            "$cnt $loc $mt $tmEqk"
                                        )
                                        recyclerItem.add(RecyclerItem(loc, tmEqk.toString(), mt.toString()))
                                    }
                                }
                                runOnUiThread {
                                    adapter = RecyclerAdapter(recyclerItem)
                                    binding.recyclerView.adapter = adapter
                                    adapter.notifyDataSetChanged()
                                }
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "오류: ${response.code()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("Eqk API test", "오류: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<Eqk>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "실패 ${t.printStackTrace()}", Toast.LENGTH_SHORT).show()
                        Log.d("Eqk API test", "실패 : ${t.printStackTrace()}")

                        val errorMessage = t.message ?: "Unknown error occurred"
                        Toast.makeText(this@MainActivity, "실패: $errorMessage", Toast.LENGTH_SHORT).show()
                        Log.d("Eqk API test", "실패: $errorMessage")
                    }

                })
            }
            typhoo.setOnClickListener {
                callTyphoo.enqueue(object : Callback<Typhoo> {
                    override fun onResponse(call: Call<Typhoo>, response: Response<Typhoo>) {
                        if (response.isSuccessful) {
                            val result: Typhoo? = response.body()
                            if (result != null) {
                                recyclerItem = mutableListOf()
                                if(result.response.header.resultMsg == "NO_DATA"){
                                    recyclerItem.add(RecyclerItem("NO_DATA", "NO_DATA","NO_DATA"))

                                }else{
                                    val data = result.response.body.items.item
                                    for (item in data) {
                                        val typName = item.typName
                                        val typLoc = item.typLoc
                                        val typWs = item.typWs
                                        val img = item.img
                                        Log.d(
                                            "Typhoo API Success",
                                            "$typName $typLoc $typWs $img"
                                        )
                                        recyclerItem.add(RecyclerItem(typLoc,typName,typWs.toString()))
                                    }
                                }
                                runOnUiThread {
                                    adapter = RecyclerAdapter(recyclerItem)
                                    binding.recyclerView.adapter = adapter
                                    adapter.notifyDataSetChanged()
                                }
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "오류: ${response.code()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("Eqk API test", "오류: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<Typhoo>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "실패 ${t.printStackTrace()}", Toast.LENGTH_SHORT).show()
                        Log.d("Typhoo API test", "실패 : ${t.printStackTrace()}")

                        val errorMessage = t.message ?: "Unknown error occurred"
                        Toast.makeText(this@MainActivity, "실패: $errorMessage", Toast.LENGTH_SHORT).show()
                        Log.d("Typhoo API test", "실패: $errorMessage")
                    }
                })
            }
        }
    }

    private fun setToDate() : String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        return current.format(formatter)
    }

    private fun setFromDate() : String{
        val current = LocalDateTime.now().minusDays(3)
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        return current.format(formatter)
    }
}