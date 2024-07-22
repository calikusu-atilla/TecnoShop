package com.example.tecnoshop.ViewModel

import ItemsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnoshop.RoomDb.Entity.ItemsModelDb
import kotlinx.coroutines.launch

// ItemsRepository'yi kullanarak ViewModel tanımlıyoruz.
class ItemViewModel (private val repository: ItemsRepository) : ViewModel() {

    val items : LiveData<List<ItemsModelDb>> = repository.items // Repository'den tüm itemleri LiveData olarak alıyoruz.

    fun insertItem(item : ItemsModelDb){  //yeni bir item eklemek için fonk tanımlanıyor
        viewModelScope.launch {           //viewModelScope içinde corutine başlatıyoruz
            repository.insertItem(item)   //Repository'yi kullanarak item ekliyoruz
        }
    }

    fun deleteItemById(id: Int){            //belirli bir id sahip itemı silmek için fonk tanımlıyoruz
        viewModelScope.launch {              //viewModelScope içinde corutine başlatıyoruz
            repository.deleteItemById(id)   //Repository'yi kullanarak item siliyoruz
        }
    }

    fun updateItem(item: ItemsModelDb){    //mevcut itemi güncellemek için fonk tanımlıyoruz
        viewModelScope.launch {           //viewModelScope içinde corutine başlatıyoruz
            repository.updateItem(item)    //Repository'yi kullanarak item güncelliyoruz
        }
    }

    fun getTotalFee(): LiveData<Double>{            //sepetteki tüm itemların toplam ücretini hesaplayan fonk tanımlıyoruz
        val toptalFee = MutableLiveData<Double>()   // toplam ücreti tutacak mutablivedata oluşturuyoruz
        viewModelScope.launch {                     //viewModelScope içinde corutine başlatıyoruz
            toptalFee.postValue(repository.getTotalFee()) } //Repository den toplam ücreti alıp MutableLiveData ya post ediyoruz
        return toptalFee                           //Toplam ücreti LiveData olarak döndürüyoruz
    }
}