package com.study.umc_chapter8_mission.adapter

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import com.study.umc_chapter8_mission.MemoActivity
import com.study.umc_chapter8_mission.R
import com.study.umc_chapter8_mission.data
import com.study.umc_chapter8_mission.database.entity.Memo
import com.study.umc_chapter8_mission.databinding.ItemBinding
import com.study.umc_chapter8_mission.db
import com.study.umc_chapter8_mission.gson
import com.study.umc_chapter8_mission.requestLauncher
import com.study.umc_chapter8_mission.result_code.ResultCode
import com.study.umc_chapter8_mission.sharedPreference
import kotlin.concurrent.thread


class MemoViewAdapter(private val Memo : MutableList<Memo>) :
    RecyclerView.Adapter<MemoViewAdapter.viewHolder>() {
    private var mainHandler = Handler(Looper.getMainLooper())
    private val editor: SharedPreferences.Editor = sharedPreference.edit()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return viewHolder(ItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.itemTitle.text=Memo[position].title
        holder.binding.itemContext.text = Memo[position].context
        holder.binding.itemSwitch.isChecked = Memo[position].checked
        holder.binding.itemLike.isChecked = Memo[position].like
        holder.binding.itemBookmark.isChecked = Memo[position].bookmark
        setColor(holder)

        holder.itemView.setOnClickListener {
            val item = Memo[position]
            val intent = Intent(it.context, MemoActivity::class.java)
            intent.putExtra(ResultCode.TITLE, item.title)
            intent.putExtra(ResultCode.CONTEXT, item.context)
            intent.putExtra(ResultCode.POSITION, position)
            intent.putExtra(ResultCode.EDIT_CHECK, true)
            mainHandler.post{
                requestLauncher.launch(intent)
            }
        }

        holder.itemView.setOnLongClickListener {
            thread {
                db.memoDao().delete(Memo[position])
                Log.d("DB test", "MemoViewAdapter 삭제")
                var temp : String? = sharedPreference.getString(data[position].id.toString(), null)
                if(temp != null){
                    editor.remove(Memo[position].id.toString())
                    editor.apply()
                    Log.d("BookMark", "delete ${Memo[position].title}")
                }
                mainHandler.post{
                    Memo.removeAt(position)
                    notifyDataSetChanged()
                }
            }
            true
        }
        holder.binding.itemSwitch.setOnClickListener {
            Memo[position].checked = holder.binding.itemSwitch.isChecked
            setColor(holder)
            thread {
                db.memoDao().update(Memo[position])
                var temp : String? = sharedPreference.getString(data[position].id.toString(), null)
                if(temp != null){
                    editor.remove(Memo[position].id.toString())
                    val value = gson.toJson(Memo[position], object : TypeToken<Memo>(){}.type)
                    val key = Memo[position].id.toString()

                    Log.d("BookMark", "bookmark switch change $position ${Memo[position].title}")

                    editor.putString(key, value)
                    editor.apply()
                }
            }
        }
        holder.binding.itemLike.setOnClickListener {
            Memo[position].like = holder.binding.itemLike.isChecked
            thread {
                db.memoDao().update(Memo[position])
                var temp : String? = sharedPreference.getString(data[position].id.toString(), null)
                if(temp != null){
                    editor.remove(Memo[position].id.toString())
                    val value = gson.toJson(Memo[position], object : TypeToken<Memo>(){}.type)
                    val key = Memo[position].id.toString()

                    Log.d("BookMark", "bookmark like change $position ${Memo[position].title}")

                    editor.putString(key, value)
                    editor.apply()
                }
            }
        }
        holder.binding.itemBookmark.setOnClickListener {
            Memo[position].bookmark = holder.binding.itemBookmark.isChecked
            thread {
                db.memoDao().update(Memo[position])
            }

            if(holder.binding.itemBookmark.isChecked){
                val value = gson.toJson(Memo[position], object : TypeToken<Memo>(){}.type)
                val key = Memo[position].id.toString()

                Log.d("BookMark", "bookmark on $position ${Memo[position].title}")

                editor.putString(key, value)
                editor.apply()
            }else{
                editor.remove(Memo[position].id.toString())
                editor.apply()
                Log.d("BookMark", "bookmark off $position ${Memo[position].title}")
            }
        }
    }

    fun setColor(holder: viewHolder){
        if(holder.binding.itemSwitch.isChecked){
            holder.binding.item.setBackgroundResource(R.color.green_black)
            holder.binding.itemTitle.setTextColor(Color.WHITE)
            holder.binding.itemContext.setTextColor(Color.WHITE)
        }else{
            holder.binding.item.setBackgroundResource(R.color.white)
            holder.binding.itemTitle.setTextColor(Color.parseColor("#FF0A1A1A"))
            holder.binding.itemContext.setTextColor(Color.parseColor("#FF0A1A1A"))
        }
    }

    override fun getItemCount(): Int {
        return Memo.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class viewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
    }
}
