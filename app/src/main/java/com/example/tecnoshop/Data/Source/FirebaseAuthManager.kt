package com.example.tecnoshop.Data.Source

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest

class FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance() //Firebase Auth işlemleri tanımlanıyor

    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {  //login fonsiyonu kullanıcıdan email ve password bilgileri alınır giriş işleminin sonucunu callback atanır. callback giriş işleminin başarılı olup olmadığını belirten bir Boolean ve hata mesajını içerebilecek bir String parametre alır.
        auth.signInWithEmailAndPassword(email, password)       //signInWithEmailAndPassword metodu, giriş işlemini başlatır
            .addOnCompleteListener { task ->                   //addOnCompleteListener metodu ile işlem tamamlandığında yapılacakları belirtir. task nesnesi, işlemin sonucunu içerir.
                if (task.isSuccessful) { // Eğer giriş işlemi başarılı olursa callback fonksiyonunu çağırarak başarı durumunu true ve hata mesajı null döner.
                    Log.d("FirebaseAuthManager", "Login successful")
                    callback(true, null)
                } else {                                       // Eğer giriş işlemi başarısız olursa callback fonksiyonunu çağırarak başarı durumunu false ve hata mesajı döner.
                    Log.e("FirebaseAuthManager", "Login failed: ${task.exception?.message}")
                    callback(false, task.exception?.message)
                }
            }
    }

    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit) {  //register fonsiyonu kullanıcıdan email ve password bilgileri alınır kayıt işleminin sonucunu callback atanır. callback giriş işleminin başarılı olup olmadığını belirten bir Boolean ve hata mesajını içerebilecek bir String parametre alır.
        auth.createUserWithEmailAndPassword(email, password)  //createUserWithEmailAndPassword metodu, kullanıcı oluşturma işlemini başlatır
            .addOnCompleteListener { task ->                   //addOnCompleteListener metodu ile işlem tamamlandığında yapılacakları belirtir. task nesnesi, işlemin sonucunu içerir.
                if (task.isSuccessful) {                      // Eğer giriş işlemi başarılı olursa callback fonksiyonunu çağırarak başarı durumunu true ve hata mesajı null döner.
                    /*val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                println("Kullanıcı adı güncellendi")
                            }
                        }*/
                    Log.d("FirebaseAuthManager", "Register successful")
                    callback(true, null)
                } else {                                       // Eğer giriş işlemi başarısız olursa callback fonksiyonunu çağırarak başarı durumunu false ve hata mesajı döner.
                    Log.e("FirebaseAuthManager", "Register failed: ${task.exception?.message}")
                    callback(false, task.exception?.message)
                }
            }
    }

    fun logout() { auth.signOut() //logout fonksiyonu mevcut kullanıcıdan çıkış yapar
         }

    fun getCurrentUser(): FirebaseUser? {  //getCurrentUser foksiyonu mevcut oturum açmış fonksiyonu döner
        return auth.currentUser  //oturum açmış olan kullanıcıyı döner. Eğer oturum açmış bir kullanıcı yoksa null döner
         }

    fun resetPassword(email: String, callback: (Boolean, String?) -> Unit) { //resetPassword fonsiyonu kullanıcıdan email bilgisini alınır alınan e-postanın sonucu callback atanır. callback girilen e-posta bilgisi başarılı olup olmadığını belirten bir Boolean ve hata oluşmuşsa hata mesajını içerebilecek bir String parametre alır.
        auth.sendPasswordResetEmail(email) // sendPasswordResetEmail metodunu belirtilen e-posta adresine şifre sıfırlama e-postası gönderir.
            .addOnCompleteListener { task ->   //addOnCompleteListener metodu ile işlem tamamlandığında yapılacakları belirtir. task nesnesi, işlemin sonucunu içerir.
                if (task.isSuccessful) {      // Eğer giriş işlemi başarılı olursa callback fonksiyonunu çağırarak başarı durumunu true ve hata mesajı null döner.
                    Log.d("FirebaseAuthManager", "Password reset email sent")
                    callback(true, null)     //callback fonksiyonunu çağırır. İlk parametre true, işlemin başarılı olduğunu gösterir. İkinci parametre null, hata olmadığını gösterir.
                } else {
                    Log.e("FirebaseAuthManager", "Password reset failed: ${task.exception?.message}")
                    callback(false, task.exception?.message)  //callback fonksiyonunu çağırır. İlk parametre false, işlemin başarısız olduğunu gösterir. İkinci parametre, hata mesajını içerir
                }
            }

    }
}