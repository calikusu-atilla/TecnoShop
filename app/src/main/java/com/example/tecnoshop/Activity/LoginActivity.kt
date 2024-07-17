package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.tecnoshop.Data.Repository.AuthRepository
import com.example.tecnoshop.Data.Source.FirebaseAuthManager
import com.example.tecnoshop.ViewModel.AuthViewModelFactory
import com.example.tecnoshop.ViewModel.AuthViewModel
import com.example.tecnoshop.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(FirebaseAuthManager()))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginViewModel.authState.observe(this, Observer { isAuthenticated ->
            if (isAuthenticated) {
                val intent = Intent (this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
                // Kullanıcı giriş yaptı
            } else {
                // Kullanıcı çıkış yaptı

            }
        })

        loginViewModel.authError.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        })




        binding.loginBtn.setOnClickListener {
            val  email = binding.emailTxt.text.toString()
            val password = binding.passwordTxt.text.toString()
            loginViewModel.register(email, password)



    }
}}
