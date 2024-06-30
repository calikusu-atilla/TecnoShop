package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       binding.startBtn.setOnClickListener{
           val intent = Intent (this@IntroActivity,MainActivity::class.java)
           startActivity(intent)
       }


    }

}