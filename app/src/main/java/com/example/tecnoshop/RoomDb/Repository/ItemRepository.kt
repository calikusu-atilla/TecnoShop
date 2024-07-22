import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tecnoshop.RoomDb.Dao.ItemsDao
import com.example.tecnoshop.RoomDb.Entity.ItemsModelDb
import com.example.tecnoshop.RoomDb.Repository.ItemRepositoryInterFace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsRepository(private val itemsDao: ItemsDao) :ItemRepositoryInterFace {

    // LiveData ile veri güncellemelerini takip etmek
    private val _items = MutableLiveData<List<ItemsModelDb>>()
    override val items: LiveData<List<ItemsModelDb>> get() = _items

    private suspend fun refreshItems() {              // Item listesini güncelleyen bir fonksiyon
        withContext(Dispatchers.IO) {                 // Arka planda IO işlemlerini gerçekleştirmek için withContext kullanıyoruz.
            _items.postValue(itemsDao.getAllItems())  //  Güncellenmiş item listesini alıp LiveData ya post ediyoruz
        }
    }

    override suspend fun insertItem(item: ItemsModelDb) {
        withContext(Dispatchers.IO) {                   // Arka planda IO işlemlerini gerçekleştirmek için withContext kullanıyoruz.
            val existingItems = itemsDao.getAllItems()  // tüm itemları alıyoruz
            val existAlready = existingItems.any { it.title == item.title }  // aynı başlığa sahip bir item olup olmadığını kontrol editoruz

            if (existAlready) {                      //eğer aynı başlığa sahip bir item varsa onu güncelliyoruz
                val existingItem = existingItems.find { it.title == item.title }?.copy(numberInCart = item.numberInCart)
                if (existingItem != null) {
                    itemsDao.updateItem(existingItem)
                }
            } else {
                itemsDao.insertItem(item)       // aynı başlığa sahip bir item yoksa yeni itemi ekliyoruz
            }
            refreshItems()                 //Item listesini güncelliyoruz
        }
    }

    override suspend fun updateItem(item: ItemsModelDb) { //var olan bir Item güncelleyen fonksiyon
        withContext(Dispatchers.IO) {             // Arka planda IO işlemlerini gerçekleştirmek için withContext kullanıyoruz.
            itemsDao.updateItem(item)             //Item güncelliyoruz
            refreshItems() }                      //Item listesini güncelliyoruz
    }

    override suspend fun deleteItemById(id: Int) {
        withContext(Dispatchers.IO) {            // Arka planda IO işlemlerini gerçekleştirmek için withContext kullanıyoruz.
            itemsDao.deleteItemById(id)          //Itemı siliyoruz
            refreshItems()                      //Item listesini güncelliyoruz
        }
    }

    override suspend fun getTotalFee(): Double {  //sepetteki tüm itemların toplam ücretini hesaplayan fonksiyon
        return withContext(Dispatchers.IO) {     // Arka planda IO işlemlerini gerçekleştirmek için withContext kullanıyoruz.
            itemsDao.getAllItems().sumOf { it.price * it.numberInCart }  //tüm itemları alıp, her itemin fiyatını ve sepetteki miktarını çarpıyoruz ve toplamını dönüyoruz
        }
    }
}

/*
sumOf -> içerisindeki ifadeleri toplar
any -> any fonksiyonu, bir koleksiyonun herhangi bir öğesinin belirli bir koşulu sağlayıp sağlamadığını kontrol eder

 */