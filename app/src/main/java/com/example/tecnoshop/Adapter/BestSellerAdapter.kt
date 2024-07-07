package com.example.tecnoshop.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tecnoshop.Activity.DetailActivity
import com.example.tecnoshop.Model.ItemsModel
import com.example.tecnoshop.databinding.ViewholderBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemsModel>):RecyclerView.Adapter<BestSellerAdapter.BestSellerViewholder>() {
    private var context : android.content.Context? = null


    class BestSellerViewholder(val binding: ViewholderBestSellerBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerAdapter.BestSellerViewholder {
        context = parent.context
        val binding = ViewholderBestSellerBinding.inflate(LayoutInflater.from(context),parent,false)
        return BestSellerViewholder(binding)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: BestSellerViewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$"+items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(RequestOptions.centerCropTransform())
            .into(holder.binding.picBestSeller)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}