package com.comst19.chapter09

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comst19.chapter09.databinding.ItemListBinding

class RVAdapter(private var coins: List<Coin>) : RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(coin : Int){
            binding.name1.text = coins[coin].korean_name
            binding.name2.text = coins[coin].english_name
            binding.market.text = coins[coin].market
        }
    }

    fun setCoins(coins: List<Coin>) {
        this.coins = coins
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}