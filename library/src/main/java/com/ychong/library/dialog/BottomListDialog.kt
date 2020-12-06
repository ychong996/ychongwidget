package com.ychong.library.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ychong.library.R
import com.ychong.library.databinding.DialogBottomListBinding
import com.ychong.library.databinding.ItemTextBinding
import com.ychong.library.listener.OnListener

class BottomListDialog(context:Context) : Dialog(context){
    private var binding:DialogBottomListBinding? = null
    private var list = ArrayList<String>()
    private var mDimAmount:Float = 0.1f
    private var bottomListener:OnListener? = null
    fun setBottomListener(listener: OnListener):BottomListDialog{
        this.bottomListener = listener
        return this
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.dialog_bottom_list,null,false)
        setContentView(binding!!.root)
        initData()
    }

    private fun initData(){

    }

    fun setData(list:List<String>):BottomListDialog{
        this.list.addAll(list)
        return this
    }

    fun build():BottomListDialog{
        show()
        setViewData()
        return this
    }

    private fun setViewData(){
        val window = window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.bottom_animation)
        window.setDimAmount(this.mDimAmount)
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val adapter = MyRecyclerAdapter(context,list)
        binding!!.recycler.layoutManager = LinearLayoutManager(context)
        binding!!.recycler.adapter = adapter


    }

}
class MyRecyclerAdapter(var context: Context,var list:List<String>) : RecyclerView.Adapter<MyRecyclerAdapter.ItemViewHolder>(){

    class ItemViewHolder(var binding:ItemTextBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding:ItemTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_text,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textTv.text = item
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()){
            0
        }else{
            list.size
        }
    }
}