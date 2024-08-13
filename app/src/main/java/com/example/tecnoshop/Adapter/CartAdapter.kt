package com.example.tecnoshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagmentCart
import com.example.tecnoshop.Helper.ChangeNumberItemsListener
import com.example.tecnoshop.Model.ItemsModel
import com.example.tecnoshop.databinding.ViewholderCartBinding

class CartAdapter(

    private val listItemSeleted: ArrayList<ItemsModel>,
    context: Context,
    //private val onItemChanged: () -> Unit
    var changeNumberItemsListener: ChangeNumberItemsListener?=null) :RecyclerView.Adapter<CartAdapter.Viewholder>() {

    class Viewholder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root) {}
    private val managmentCart= ManagmentCart(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = listItemSeleted.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = listItemSeleted[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "${item.price} TL"
        holder.binding.totalEachItem.text = "${Math.round(item.numberInCart * item.price)} TL" //Math.round bir sayıyı en yakın tam sayıya yuvarlamak için kullanılır. Bu işlev, Float veya Double türünde bir değeri kabul eder ve bir Long türünde döner.
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)

        holder.binding.plusCartBtn.setOnClickListener {

            /*val updatedItem = item.copy(numberInCart = item.numberInCart + 1)
            updateItemList(position, updatedItem)
            onItemChanged() */

            managmentCart.plusItem(listItemSeleted,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }



        holder.binding.minusCartBtn.setOnClickListener {

            /*if (item.numberInCart > 1) {
                val updatedItem = item.copy(numberInCart = item.numberInCart - 1)
                updateItemList(position, updatedItem)
                onItemChanged()
            }*/

            managmentCart.minusItem(listItemSeleted,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()

                }

            })
        }
    }



   /* private fun updateItemList(position: Int, updatedItem: ItemsModel) {
        listItemSeleted[position] = updatedItem
        notifyItemChanged(position)
    }

    fun updateItemList(newList: List<ItemsModelDb>) {
        listItemSeleted.clear()
        listItemSeleted.addAll(newList)
        notifyDataSetChanged()
    }*/


}
