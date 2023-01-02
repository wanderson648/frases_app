package com.example.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.frases.R
import com.example.frases.databinding.ActivityMainBinding
import com.example.frases.ui.incluirFrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupFabListener()
    }


    private fun setupFabListener() {
        binding.btnFabAdd.setOnClickListener {
            startActivity(Intent(this, IncluirFraseActivity::class.java))
        }
    }
}