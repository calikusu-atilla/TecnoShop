package com.example.tecnoshop.RoomDb.Repository

import androidx.lifecycle.MutableLiveData
import com.example.tecnoshop.RoomDb.Dao.CartDao
import com.example.tecnoshop.RoomDb.Entity.CartItem

abstract class CartRepository(private val cartDao: CartDao) : CartRepositoryInterFace {





    /*
    override suspend fun insert(cartItem: CartItem) = cartDao.insert(cartItem)
    override suspend fun delete(cartItem: CartItem) = cartDao.delete(cartItem)
    override suspend fun update(cartItem: CartItem) = cartDao.update(cartItem)
    override suspend fun getAllCartItems(): List<CartItem> = cartDao.getAllCartItems()
*/
}