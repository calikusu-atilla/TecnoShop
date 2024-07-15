package com.example.tecnoshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.example.tecnoshop.Adapter.CartAdapter
import com.example.tecnoshop.Helper.ChangeNumberItemsListener
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax :Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        managmentCart=ManagmentCart(this)

        setVeriable()
        initCartList()
        calculateCart()

    }

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

    private fun initCartList() {
       binding.cartView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter=CartAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }

        })
    }

    private fun setVeriable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}