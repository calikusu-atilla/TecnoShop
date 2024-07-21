package com.example.tecnoshop.Data.Repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

interface AuthRepositoryInterFace {

    fun login (email: String, password: String, callback: (Boolean,String?) -> Unit)
    fun resetPassword (email: String, callback: (Boolean, String?) -> Unit)
    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit)
    fun getCurrentUser(): FirebaseUser?
    fun logout()
    fun isUserLoggedIn(): Boolean
}