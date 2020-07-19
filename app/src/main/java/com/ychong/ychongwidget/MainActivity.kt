package com.ychong.ychongwidget

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ychong.library.LoadingDialog
import com.ychong.ychongwidget.databinding.ActivityMainBinding

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
                        startActivity(Intent(this@MainActivity,LoadingDialogActivity::class.java))
                    }
                    "" ->{

                    }
                }
            }

        })

    }
}