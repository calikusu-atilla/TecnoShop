package com.example.tecnoshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tecnoshop.Data.Repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _authState = MutableLiveData<Boolean>()
    private val _authError = MutableLiveData<String?>()

    val authState: LiveData<Boolean> get() = _authState
    val authError: LiveData<String?> get() = _authError

    fun login(email: String, password: String) {
        authRepository.register(email,password) { success, error ->
            if (success) {
                _authState.postValue(true)
            } else {
                _authError.postValue(error)
            }
        }
    }


    fun register (email: String, password: String){
        authRepository.register(email, password) {success, error ->
            if (success){
                _authState.postValue(true)
            }else {
                _authError.postValue(error)
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _authState.value = false
    }



    fun getCurrentUser(): FirebaseUser? = authRepository.getCurrentUser()
}


