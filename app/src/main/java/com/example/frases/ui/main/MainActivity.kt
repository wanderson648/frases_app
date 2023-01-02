package com.example.frases.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frases.R
import com.example.frases.data.Phrase
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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.listOfPhrases.observe(this) { list ->
            Log.i("JPInfo", "Lista Recuperada $list")
            updateList(list)
        }
    }

    private fun updateList(list: List<Phrase>) {
        if(list.isEmpty()) {
            binding.rvListPhrase.visibility = View.GONE
            binding.txtMessage.visibility = View.VISIBLE
        } else {
            binding.txtMessage.visibility = View.GONE
            binding.rvListPhrase.visibility = View.VISIBLE
            binding.rvListPhrase.adapter = FrasesAdapter(list)
            binding.rvListPhrase.layoutManager = LinearLayoutManager(this)
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

        binding.btnFabAdd.setOnLongClickListener {
            viewModel.clearListOfPhrases()
            Toast.makeText(
                this,
                R.string.list_clear_success,
                Toast.LENGTH_LONG
            ).show()
            it.isLongClickable
        }
    }

    companion object {
        const val RETURN_PHRASE = "retorno_frase"
    }
}