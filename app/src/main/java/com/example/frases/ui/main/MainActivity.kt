package com.example.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.frases.R
import com.example.frases.data.Phrase
import com.example.frases.databinding.ActivityMainBinding
import com.example.frases.ui.incluirFrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val returnPhrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if(activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if(it.hasExtra(RETURN_PHRASE)) {
                    Log.i("testInfo", "Retorno: ${it.getParcelableExtra<Phrase>(RETURN_PHRASE)}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupFabListener()
    }


    private fun setupFabListener() {
        binding.btnFabAdd.setOnClickListener {
            Intent(this, IncluirFraseActivity::class.java).let {
                returnPhrase.launch(it)
            }
        }
    }


    companion object {
        const val RETURN_PHRASE = "retorno_frase"
    }
}