package com.example.chapter5_exercise

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter5_exercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var memoArray = ArrayList<MemoModel>()
    private var trashArray = ArrayList<MemoModel>()
    private var count = 0
    private lateinit var memoListAdapter: MemoListAdapter

    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 0) {
                val memoIntent = it.data
                if(memoIntent != null) {
                    val model: MemoModel

                    model = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        memoIntent.getSerializableExtra("memo", MemoModel::class.java)!!
                    } else {
                        (memoIntent.getSerializableExtra("memo") as MemoModel?)!!
                    }

                    if(count == 0) {
                        memoArray.add(model)
                    } else {
                        memoListAdapter.addItem(model)
                    }

                    Log.d("memo1", memoArray.toString())
                    initRecycler()
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val popupView = LayoutInflater.from(this).inflate(R.layout.fab_description, null)
        val popup = PopupWindow(
            popupView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        ).apply {
            isFocusable = true
            elevation = 10f
        }

        binding.floatingWriteBtn.setOnClickListener {
            val intent = Intent(applicationContext, MemoActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        binding.floatingWriteBtn.setOnLongClickListener {
            popup.showAsDropDown(binding.floatingWriteBtn, -100, -(binding.floatingWriteBtn.height+150))
            Handler(Looper.getMainLooper()).postDelayed({
                if (popup.isShowing) {
                    popup.dismiss()
                }
            }, 3000)
            true
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val model: MemoModel?
        val previous: MemoModel?
        model = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra("memo", MemoModel::class.java)
        } else {
            (intent?.getSerializableExtra("memo") as MemoModel?)
        }
        previous = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra("previous", MemoModel::class.java)
        } else {
            (intent?.getSerializableExtra("previous") as MemoModel?)
        }

        Log.d("memo1", "changed: $model")
        Log.d("memo1", "previous: $previous")
        if(previous != null && model != null) {
            Log.d("memo1", "바뀜")
            memoArray.removeAt(memoArray.indexOf(previous))
            memoListAdapter.addItem(model)
            initRecycler()
        }
    }

    private fun initRecycler() {
        Log.d("count", "count: $count")
        if (count == 0) {
            Log.d("init", "init recycler")
            val gridLayoutManager = GridLayoutManager(applicationContext, 3)
            memoListAdapter = MemoListAdapter(memoArray, applicationContext, trashArray)
            binding.memoList.layoutManager = gridLayoutManager
            binding.memoList.adapter = memoListAdapter
            binding.memoList.visibility = View.VISIBLE
            memoListAdapter.notifyDataSetChanged()
        }
        binding.memoList.adapter?.notifyDataSetChanged()
        Log.d("memo1", "갯수" +binding.memoList.adapter?.itemCount.toString())
        count++

    }


}