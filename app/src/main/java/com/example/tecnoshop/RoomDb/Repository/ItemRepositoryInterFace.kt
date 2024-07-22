package com.example.tecnoshop.RoomDb.Repository

import androidx.lifecycle.LiveData
import com.example.tecnoshop.RoomDb.Entity.ItemsModelDb

interface ItemRepositoryInterFace {

    // Tüm itemleri LiveData olarak saklayan bir özellik tanımlıyoruz.
    val items : LiveData<List<ItemsModelDb>>

    // Yeni bir item eklemek için kullanılan fonksiyon.
    suspend fun insertItem(item: ItemsModelDb)

    // Mevcut bir itemi güncellemek için kullanılan fonksiyon.
    suspend fun updateItem(item: ItemsModelDb)

    // Belirli bir id'ye sahip itemi silmek için kullanılan fonksiyon.
    suspend fun deleteItemById(id: Int)

    // Sepetteki tüm itemlerin toplam ücretini hesaplayan fonksiyon.
    suspend fun getTotalFee(): Double
}