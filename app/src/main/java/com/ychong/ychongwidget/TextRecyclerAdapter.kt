package com.ychong.ychongwidget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ychong.ychongwidget.databinding.ItemTextBinding

class TextRecyclerAdapter(context: Context,var list:MutableList<String>)
    :RecyclerView.Adapter<TextRecyclerAdapter.ItemViewHolder>(){
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
    }

    class ItemViewHolder(var binding: ItemTextBinding):RecyclerView.ViewHolder(binding.root)

}