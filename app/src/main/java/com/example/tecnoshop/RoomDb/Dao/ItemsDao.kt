package com.example.tecnoshop.RoomDb.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tecnoshop.RoomDb.Entity.ItemsModelDb

@Dao
interface ItemsDao {

    // Veritabanına bir item eklemek için kullanılır. Eğer item zaten mevcutsa, üzerine yazılır.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(Item: ItemsModelDb)

    // Veritabanındaki bir itemi güncellemek için kullanılır.
    @Update
    suspend fun updateItem(Item: ItemsModelDb)

    // Belirli bir id'ye sahip itemi veritabanından silmek için kullanılır.
    @Query("DELETE FROM items WHERE id = :id")
    suspend fun deleteItemById(id: Int)

    // Veritabanındaki tüm itemleri getirir.
    @Query ("SELECT * FROM items")
    suspend fun getAllItems(): List<ItemsModelDb>
}


/*
onConflict = OnConflictStrategy.REPLACE:
Bu strateji, eklenmeye çalışılan item zaten varsa, üzerine yazılacağını belirtir.
Yani, mevcut bir item ile çakışma olduğunda, eski item yenisiyle değiştirilir.
*/