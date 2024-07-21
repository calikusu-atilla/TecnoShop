package com.example.tecnoshop.RoomDb.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tecnoshop.RoomDb.Dao.CartDao
import com.example.tecnoshop.RoomDb.Entity.CartItem

@Database (entities = [CartItem::class], version = 1)
abstract class DataDb : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object{
        @Volatile
        private var INSTANCE : DataDb? = null

        fun getDatabase(context : Context): DataDb {
            return INSTANCE ?: synchronized(this) {
                val  instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataDb::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


