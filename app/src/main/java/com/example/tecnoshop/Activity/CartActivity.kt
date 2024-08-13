package com.example.tecnoshop.Activity

import ItemsRepository
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.example.tecnoshop.Adapter.CartAdapter
import com.example.tecnoshop.Helper.ChangeNumberItemsListener
import com.example.tecnoshop.RoomDb.Database.DataDb
import com.example.tecnoshop.ViewModel.ItemViewModel
import com.example.tecnoshop.ViewModel.ItemViewModelFactory
import com.example.tecnoshop.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var managmentCart: ManagmentCart
    private var tax :Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        var managmentCart = ManagmentCart(this)

        //ViewModel ve Repository entegrasyonu
        val itemsDao = DataDb.getDatabase(application).cartDao()
        val itemsRepository = ItemsRepository(itemsDao)
        val viewModelFactory =  ItemViewModelFactory(itemsRepository)
        itemViewModel = ViewModelProvider(this,viewModelFactory).get(ItemViewModel::class.java)

        setVeriable()
        initCartList()
        calculateCart()

        //Cart item'larının gözlemlenmesi
       /* itemViewModel.items.observe(this) { items ->
            (binding.cartView.adapter as CartAdapter).updateItemList(items)
            calculateCart()
        }*/

    }

    /*private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 0.0
        val totalFee = itemViewModel.getTotalFee()
        tax =Math.round((totalFee * percentTax) * 100) / 100.0
        val  total = Math.round((totalFee + tax + delivery) * 100) /100
        val  itemTotal = Math.round(totalFee * 100) / 100

        with(binding) {
            totalFeeTxt.text = "$itemTotal Tl"
            taxTxt.text = "$tax TL"
            deliveryTxt.text = "$delivery TL"
            totalTxt.text = "$total TL"
        }
    }*/


    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = Math.round((managmentCart.getTotalFee()* percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery)* 100)/100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100

        with(binding) {
            totalFeeTxt.text = "$itemTotal Tl"
            taxTxt.text = "$tax Tl"
            deliveryTxt.text = "$delivery Tl"
            totalTxt.text = "$total TL"
        }
        
    }




  /* private fun initCartList(){
        binding.cartView.layoutManager = LinearLayoutManager (this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter = CartAdapter(mutableListOf(),this) {
            calculateCart()
        }
    }*/

    private fun initCartList() {
       binding.cartView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter=CartAdapter(managmentCart.getListCart(),this,object :
            ChangeNumberItemsListener {
            override fun onChanged() {
                calculateCart()
            }

        })
    }

    private fun setVeriable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}