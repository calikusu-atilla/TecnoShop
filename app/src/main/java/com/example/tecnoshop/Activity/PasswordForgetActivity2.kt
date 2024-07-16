package com.example.tecnoshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ActivityPasswordForget2Binding

class PasswordForgetActivity2 : BaseActivity() {
    private lateinit var binding: ActivityPasswordForget2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordForget2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}