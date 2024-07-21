package com.example.tecnoshop.RoomDb.Repository

import com.example.tecnoshop.RoomDb.Entity.CartItem

interface CartRepositoryInterFace {

    suspend fun insert(cartItem: CartItem)
    suspend fun update(cartItem: CartItem)
    suspend fun delete(cartItem: CartItem)
    suspend fun getAllCartItems(): List<CartItem>
}