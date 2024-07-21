package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.tecnoshop.Data.Repository.AuthRepository
import com.example.tecnoshop.Data.Source.FirebaseAuthManager
import com.example.tecnoshop.R
import com.example.tecnoshop.ViewModel.AuthViewModel
import com.example.tecnoshop.ViewModel.AuthViewModelFactory
import com.example.tecnoshop.databinding.ActivityProfilBinding

class ProfilActivity : BaseActivity() {

    private lateinit var binding: ActivityProfilBinding
    private val authViewModel : AuthViewModel by viewModels { AuthViewModelFactory(AuthRepository(FirebaseAuthManager())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bottomNavigation()
        observeAuthState()

        //Kullanıcı oturum açmış mı kontrol et
        checkUserLoggedIn()

    }



    private fun bottomNavigation() {

        binding.backBtn.setOnClickListener { finish() }
        binding.profilBtn.setOnClickListener { startActivity(Intent(this,EditProfilActivity::class.java)) }
        binding.logOutBtn.setOnClickListener { logOutUser() }


    }

    private fun logOutUser(){
        authViewModel.logout()
        /*Toast.makeText(this,"Çıkış yapıldı",Toast.LENGTH_LONG).show()
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()*/
    }

    private fun observeAuthState(){

        authViewModel.authState.observe(this, Observer { isAuthenticated ->
            if (!isAuthenticated){
                Toast.makeText(this,"Çıkış yapıldı",Toast.LENGTH_LONG).show()
                val intent = Intent(this@ProfilActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
               /* Toast.makeText(this,"Oturum açılmamış, Lütfen giriş yapın.",Toast.LENGTH_LONG).show()
                val intent = Intent(this@ProfilActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()*/
            }
        })
    }


    private fun checkUserLoggedIn() {
        val currentUser = authViewModel.getCurrentUser()
        if (currentUser == null) {
            Toast.makeText(this, "Oturum açılmamış, Lütfen giriş yapın.", Toast.LENGTH_LONG).show()
            val intent = Intent(this@ProfilActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}