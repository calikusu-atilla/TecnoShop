package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ActivityProfilBinding

class ProfilActivity : BaseActivity() {

    private lateinit var binding: ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bottomNavigation()
    }

    private fun bottomNavigation() {

        binding.backBtn.setOnClickListener { finish() }
        binding.profilBtn.setOnClickListener { startActivity(Intent(this,EditProfilActivity::class.java)) }

    }
}