package com.ychong.ychongwidget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ychong.ychongwidget.databinding.ItemTextBinding

class TextRecyclerAdapter(context: Context, private var list:MutableList<String>)
    :RecyclerView.Adapter<TextRecyclerAdapter.ItemViewHolder>(){
    private var itemClickListener:ItemClickListener? = null
    fun setItemClickListener(itemClickListener:ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty())
            0
        else
            list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textTv.text = item
        holder.binding.itemLayout.setOnClickListener{
            if (itemClickListener!=null){
                itemClickListener!!.click(item)
            }
        }
    }

    class ItemViewHolder(var binding: ItemTextBinding):RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener{
        fun click(item:String)
    }

}