package com.example.tecnoshop.RoomDb.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// "items" adında bir tablo tanımlıyoruz.
@Entity(tableName = "items")
data class ItemsModelDb(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Item başlığı
    val title: String,

    // Sepetteki item sayısı
    val numberInCart: Int,

    // Item fiyatı
    val price: Double
)
