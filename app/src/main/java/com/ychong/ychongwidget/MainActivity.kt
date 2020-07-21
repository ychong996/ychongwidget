package com.ychong.ychongwidget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ychong.ychongwidget.databinding.ActivityMainBinding
import com.ychong.ychongwidget.dialog.*

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val adapter = TextRecyclerAdapter(this,ListUtils.getList())
        binding!!.recycler.layoutManager = LinearLayoutManager(this)
        binding!!.recycler.adapter = adapter

        adapter.setItemClickListener(object : TextRecyclerAdapter.ItemClickListener{
            override fun click(item: String) {
                when(item){
                    "LoadingDialog" ->{
                        startActivity(Intent(this@MainActivity,
                            LoadingDialogActivity::class.java))
                    }
                    "TipsDialog" ->{
                        startActivity(Intent(this@MainActivity,TipsDialogActivity::class.java))
                    }
                    "InputDialog" ->{
                        startActivity(Intent(this@MainActivity,
                            InputDialogActivity::class.java))

                    }
                    "MessageDialog" ->{
                        startActivity(Intent(this@MainActivity,
                            MessageDialogActivity::class.java))
                    }
                    "ResultDialog" ->{
                        startActivity(Intent(this@MainActivity,
                        ResultDialogActivity::class.java))
                    }
                    "ToastDialog" ->{
                        startActivity(Intent(this@MainActivity,
                        ToastDialogActivity::class.java))
                    }
                }
            }

        })

    }
}