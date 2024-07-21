package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tecnoshop.Data.Repository.AuthRepository
import com.example.tecnoshop.Data.Source.FirebaseAuthManager
import com.example.tecnoshop.R
import com.example.tecnoshop.ViewModel.AuthViewModel
import com.example.tecnoshop.ViewModel.AuthViewModelFactory
import com.example.tecnoshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    private val authViewModel : AuthViewModel by viewModels {AuthViewModelFactory(AuthRepository(FirebaseAuthManager()))  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        CheckCunrentUSer()

       binding.startBtn.setOnClickListener{
           val intent = Intent (this@IntroActivity,LoginActivity::class.java)
           startActivity(intent)
       }

        binding.registerTxt.setOnClickListener {
            val intent = Intent(this@IntroActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun CheckCunrentUSer() {
        val currentUser = authViewModel.getCurrentUser()
        if (currentUser != null) {
            val intent = Intent(this@IntroActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}