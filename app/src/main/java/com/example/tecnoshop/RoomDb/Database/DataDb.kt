package com.example.tecnoshop.RoomDb.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tecnoshop.RoomDb.Dao.ItemsDao
import com.example.tecnoshop.RoomDb.Entity.ItemsModelDb

//entities parametresi, bu veritabanında kullanılacak veri modellerini (tabloları) belirtir. Burada, ItemsModelDb veri modeli kullanılıyor.
@Database (entities = [ItemsModelDb::class], version = 1)
abstract class DataDb : RoomDatabase() {  //Bu sınıf vertabanı ve Dao lar arasındaki bağlantıyı sağlar
    abstract fun cartDao(): ItemsDao      //ItemsDao veri tabanı işlemlerini gerçekleştirmek için dao kullanılır

    companion object{
        @Volatile  //değişkeninin farklı thread'ler tarafından düzgün bir şekilde görülebilmesini sağlar. Böylece bir thread'de yapılan değişiklikler diğer thread'lerde de anında görünür olur.
        private var INSTANCE : DataDb? = null

        fun getDatabase(context : Context): DataDb {
                                                          // INSTANCE değişkeni null değilse, onun değerini döndür.
                                                          // Eğer null ise, synchronized bloğuna gir.
            return INSTANCE ?: synchronized(this) {
                                                        // synchronized bloğunda, INSTANCE değişkeninin tekrar kontrol edilmesi.
                                                        // Başka bir thread aynı anda bu bloğa girmiş olabilir, bu yüzden tekrar kontrol ediyoruz.
                val  instance = Room.databaseBuilder(     //room veritabanı oluşturur
                    context.applicationContext,           //uygulamaya bağlanır
                    DataDb::class.java,                   //veri tabanı sınıf
                    "database"                      //veri tabanı adı
                ).build()
                INSTANCE = instance  //oluşturulan veritabanı ınstance değişkenine atanır ve sonraki çağrılarda kullanılır
                instance
            }
        }
    }
}


