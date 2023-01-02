package com.example.frases.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.frases.databinding.ActivityMainBinding
import com.example.frases.ui.incluirFrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val returnPhrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETURN_PHRASE)) {
                    viewModel.savePhrases(it.getParcelableExtra(RETURN_PHRASE)!!)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        startData()
        setupFabListener()
        setupObservers()
    }

    private fun setupObservers() {
        setupUpdateList()
    }

    private fun setupUpdateList() {
        viewModel.listOfPhrases.observe(this) { list ->
            Log.i("JPInfo", "setupUpdateList:Lista Recuperada $list")
        }
    }

    private fun startData() {
        viewModel.startData()
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