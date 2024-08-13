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
import androidx.lifecycle.ViewModel
import com.example.tecnoshop.Data.Repository.AuthRepository
import com.example.tecnoshop.Data.Source.FirebaseAuthManager
import com.example.tecnoshop.R
import com.example.tecnoshop.ViewModel.AuthViewModel
import com.example.tecnoshop.ViewModel.AuthViewModelFactory
import com.example.tecnoshop.databinding.ActivityPasswordForget1Binding

class PasswordForgetActivity1 : BaseActivity() {
    private lateinit var binding : ActivityPasswordForget1Binding
    private val authViewModel : AuthViewModel by viewModels { AuthViewModelFactory(AuthRepository(FirebaseAuthManager(this))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordForget1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.backBtn.setOnClickListener { finish() }
        binding.registerTxt.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent) }
        binding.resetPasswordBtn.setOnClickListener {            // resetPasswordBtn tıklama olayı dinlenir
            val email = binding.emailTxt.text.toString().trim()
            if (email.isNotEmpty()) {                            // kullanıcı bir e-posta girerse resetPassword foksiyonu çağrılır aksi taktirde bir uyarı mesajı gösterilir
                resetPassword(email)
            } else {
                Toast.makeText(this, "Lütfen geçerli bir e-posta adresi giriniz", Toast.LENGTH_SHORT).show()
            }
        }

        observeAuthState()  // foksiyonu çağrılarak authViewModel durumunu gözlemlemeya başlanır
    }

    private fun resetPassword(email: String) {
        authViewModel.resetPassword(email)  // resetPassword fonksiyonu, authViewModel'in resetPassword metodunu çağırır ve kullanıcıdan alınan e-posta adresini argüman olarak geçirir.
    }

    private fun observeAuthState() {
        authViewModel.authState.observe(this, Observer { isSuccess -> // authState gözlemler ve sonuca göre başarılı olup olmadığı kontrol edilir
            if (isSuccess) {          //Başarılı ise başarı mesajı döner
                val intent = Intent (this,LoginActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Password reset email sent", Toast.LENGTH_SHORT).show()
            }
        })

        authViewModel.authError.observe(this, Observer { error ->   //authError gözlemlenir hata varsa hata mesajı bir tost mesajı olarak döner
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}