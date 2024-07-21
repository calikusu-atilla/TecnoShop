package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tecnoshop.Data.Repository.AuthRepository
import com.example.tecnoshop.Data.Source.FirebaseAuthManager
import com.example.tecnoshop.ViewModel.AuthViewModel
import com.example.tecnoshop.ViewModel.AuthViewModelFactory
import com.example.tecnoshop.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity()  {
    
    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModels { AuthViewModelFactory(AuthRepository(FirebaseAuthManager())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        binding.backBtn.setOnClickListener { finish() } 
        
        authViewModel.authState.observe( this, Observer { isAuthenticated -> 
            if (isAuthenticated) {
                val intent = Intent (this@RegisterActivity,LoginActivity::class.java)
                startActivity(intent)
            }else {
                
            }
        })
        
        authViewModel.authError.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        })
        
        binding.registerBtn.setOnClickListener {
            val email = binding.emailTxt.text.toString()
            val password = binding.passwordTxt.text.toString()
            authViewModel.register(email,password)

        }

    
    }
    
    }
