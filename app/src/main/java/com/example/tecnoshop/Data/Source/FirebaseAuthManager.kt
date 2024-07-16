package com.example.tecnoshop.Data.Source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance() //Firebase Auth işlemleri tanımlanıyor

    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {  //login fonsiyonu kullanıcıdan email ve password bilgileri alınır giriş işleminin sonucunu callback atanır. callback giriş işleminin başarılı olup olmadığını belirten bir Boolean ve hata mesajını içerebilecek bir String parametre alır.
        auth.signInWithEmailAndPassword(email, password)       //signInWithEmailAndPassword metodu, giriş işlemini başlatır
            .addOnCompleteListener { task ->                   //addOnCompleteListener metodu ile işlem tamamlandığında yapılacakları belirtir. task nesnesi, işlemin sonucunu içerir.
                if (task.isSuccessful) {                       // Eğer giriş işlemi başarılı olursa callback fonksiyonunu çağırarak başarı durumunu true ve hata mesajı null döner.
                    callback(true, null)
                } else {                                       // Eğer giriş işlemi başarısız olursa callback fonksiyonunu çağırarak başarı durumunu false ve hata mesajı döner.
                    callback(false, task.exception?.message)
                }
            }
    }

    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit){  //register fonsiyonu kullanıcıdan email ve password bilgileri alınır kayıt işleminin sonucunu callback atanır. callback giriş işleminin başarılı olup olmadığını belirten bir Boolean ve hata mesajını içerebilecek bir String parametre alır.

        auth.createUserWithEmailAndPassword(email, password)  //createUserWithEmailAndPassword metodu, kullanıcı oluşturma işlemini başlatır
            .addOnCompleteListener {task ->                   //addOnCompleteListener metodu ile işlem tamamlandığında yapılacakları belirtir. task nesnesi, işlemin sonucunu içerir.
                if (task.isSuccessful) {                      // Eğer giriş işlemi başarılı olursa callback fonksiyonunu çağırarak başarı durumunu true ve hata mesajı null döner.
                    callback(true, null)
                }else {                                       // Eğer giriş işlemi başarısız olursa callback fonksiyonunu çağırarak başarı durumunu false ve hata mesajı döner.
                    callback(false, task.exception?.message)
                }
            }
    }

    fun logout() {
        auth.signOut()  //logout fonksiyonu mevcut kullanıcıdan çıkış yapar
    }

    fun getCurrentUser (): FirebaseUser? {  //getCurrentUser foksiyonu mevcut oturum açmış fonksiyonu döner
        return auth.currentUser  //oturum açmış olan kullanıcıyı döner. Eğer oturum açmış bir kullanıcı yoksa null döner
    }

}