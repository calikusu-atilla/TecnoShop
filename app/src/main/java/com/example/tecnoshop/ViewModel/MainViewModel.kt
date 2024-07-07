package com.example.tecnoshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tecnoshop.Model.CategoryModel
import com.example.tecnoshop.Model.ItemsModel
import com.example.tecnoshop.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import java.lang.ref.Reference
import kotlin.jvm.internal.Ref

class MainViewModel:ViewModel() {


        private val firebaseDatabase = FirebaseDatabase.getInstance()  // Firebese veritabanı oluşturuldu
        private val _banner = MutableLiveData<List<SliderModel>>()   //banner verileri tutulmak için mutablelivedata oluşturuldu -> mutablelivedata: gözlemlenerek veri değişikliklerine tepki veri
        private val _category = MutableLiveData<MutableList<CategoryModel>>() // Category verilerini tutmak için mutablelivedata oluşturuldu
        private val _bestseller = MutableLiveData<MutableList<ItemsModel>>()  // BestSeller verilerini tutmak için mutableLivedata oluşturuldu



    val banners : LiveData<List<SliderModel>> = _banner
    val category : LiveData<MutableList<CategoryModel>> = _category
    val bestseller : LiveData<MutableList<ItemsModel>> = _bestseller

    fun loadBanner(){  // Banner verilerini yükleyen bir fonksiyon oluşturuldu
        val Ref = firebaseDatabase.getReference("Banner") //FireBasedeki Banner referensına ulaşılıyor
        Ref.addValueEventListener(object : ValueEventListener{ //firebase verilerinde değişik olduğunda veri çekmek için dinleyici oluşuruldu.
            override fun onDataChange(snapshot: DataSnapshot) { //onDataChange fonksiyonu, veritabanındaki veriler her değiştiğinde tetiklenir. snapshot parametresi üzerinden veriler okunur ve SliderModel türündeki veriler lists listesine eklenir.
                val lists = mutableListOf<SliderModel>() // SliderModel nesnelerini tutmak için boş bir liste oluşturuldu
                for (child in snapshot.children){  // her bir nesne tek tek gezilir
                    val list = child.getValue(SliderModel::class.java) // her nesneyi tek tek SliderModel nesnesine dönüştürür
                    if (list != null){  // eğer liste boş değilse listeye eklenir
                        lists.add(list!!)
                    }
                }
                _banner.value = lists // _banner listesi yeni liste ile güncellenir
            }

            override fun onCancelled(error: DatabaseError) { // veri çekilirken hata oluşursa çağrılır ve hata durumu log.e kaydolur

            }
        })
    }


    fun loadCategory () {//Category verilerini yükleyen bir fonksiyon oluşturuldu
        val Ref = firebaseDatabase.getReference("Category") // FireBasedeki Category referansına ulaşılıyor
        Ref.addValueEventListener(object :ValueEventListener{ // değişiklik olduğunda veri çekmek için dinleyici oluşturuldu
            override fun onDataChange(snapshot: DataSnapshot) {  //onDataChange fonksiyonu, veritabanındaki veriler her değiştiğinde tetiklenir.
                val lists = mutableListOf<CategoryModel>() // Category verilerini tutmak için boş bir liste oluşturuldu
                for (child in snapshot.children){ // her bir nesne tek tek gezilir
                    val list = child.getValue(CategoryModel::class.java) // her bir nesneyi tek tek category nesnesine dönüştürür
                    if (list !=null) { //eğer boş değilse listeye eklenir
                        lists.add(list!!)
                    }
            }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadBestSeller () {
        val Ref = firebaseDatabase.getReference("BestSeller")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (child in snapshot.children){
                    val list =child.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list!!)
                    }

                }
                _bestseller.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}