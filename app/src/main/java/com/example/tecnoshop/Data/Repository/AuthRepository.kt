package com.example.tecnoshop.Data.Repository

import com.example.tecnoshop.Data.Source.FirebaseAuthManager
import com.google.firebase.auth.FirebaseUser

class AuthRepository(private val firebaseAuthManager: FirebaseAuthManager) {  //FirebaseAuthManager sınıfının işlevlerini kullanarak Authentication işlemlerini yönetir.

    //firebaseAuthManager.login(email, password, callback) ifadesi, FirebaseAuthManager sınıfındaki login fonksiyonunu çağırır ve gerekli parametreleri iletir.
    fun login (email: String, password: String, callback: (Boolean, String?) ->Unit) {
        firebaseAuthManager.login(email,password, callback)
    }

    //firebaseAuthManager.register(email, password, callback) ifadesi, FirebaseAuthManager sınıfındaki register fonksiyonunu çağırır ve gerekli parametreleri iletir.
    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuthManager.register(email, password, callback)
    }

    //firebaseAuthManager.logout() ifadesi, FirebaseAuthManager sınıfındaki logout fonksiyonunu çağırır.
    fun logout() {
        firebaseAuthManager.logout()
    }

    //FirebaseAuthManager sınıfındaki getCurrentUser fonksiyonunu çağırır ve döndürülen FirebaseUser nesnesini döner.
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthManager.getCurrentUser()
    }


    // AuthRepository sınıfı, FirebaseAuthManager sınıfının işlevlerini kullanarak Authentication işlemlerini yönetir.
    // login, register, logout, ve getCurrentUser fonksiyonları, FirebaseAuthManager sınıfındaki ilgili fonksiyonları çağırır ve işlevsellik sağlar.
    // Bu sınıf, Firebase Authentication işlemlerinin merkezi bir noktasını sağlar ve bu işlemleri yönetir. Bu, kodun daha modüler ve yönetilebilir olmasını sağlar.
}