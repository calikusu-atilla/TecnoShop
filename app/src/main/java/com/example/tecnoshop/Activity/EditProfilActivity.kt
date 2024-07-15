package com.example.tecnoshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ActivityEditProfilBinding

class EditProfilActivity : BaseActivity() {

    private lateinit var binding: ActivityEditProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}