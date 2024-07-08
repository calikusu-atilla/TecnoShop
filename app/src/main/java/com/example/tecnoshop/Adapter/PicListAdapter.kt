package com.example.tecnoshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tecnoshop.databinding.ViewholderBestSellerBinding

class PicListAdapter(val items:MutableList<String>,val picMain: ImageView): RecyclerView.Adapter<PicListAdapter.Viewholder>() {


    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    inner class Viewholder(val binding: ViewholderBestSellerBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderBestSellerBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PicListAdapter.Viewholder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.picBestSeller)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Glide.with(holder.itemView.context)
                .load(items[position])
                .into(picMain)
        }
        if (selectedPosition==position){

        }


    }

    override fun getItemCount(): Int = items.size
}