package com.example.tecnoshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ViewholderBestSellerBinding
import com.example.tecnoshop.databinding.ViewholderCapasiteBinding
import com.example.tecnoshop.databinding.ViewholderPicListBinding

class CapasiteListAdapter(val items:MutableList<String>): RecyclerView.Adapter<CapasiteListAdapter.Viewholder>() {


    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    inner class Viewholder(val binding: ViewholderCapasiteBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapasiteListAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderCapasiteBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CapasiteListAdapter.Viewholder, position: Int) {

        holder.binding.capasiteTxt.text=items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }
        if (selectedPosition==position){
            holder.binding.capasiteLayout.setBackgroundResource(R.drawable.grey_bg_selected) //buraları sonra düzenle aynı şekilde piclist adaptede
            holder.binding.capasiteTxt.setTextColor(context.resources.getColor(R.color.black))
        } else {
            holder.binding.capasiteLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.capasiteTxt.setTextColor(context.resources.getColor(R.color.Quaternary_Dark))
        }


    }

    override fun getItemCount(): Int = items.size
}