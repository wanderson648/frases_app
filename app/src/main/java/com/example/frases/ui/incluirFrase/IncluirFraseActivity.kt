package com.example.frases.ui.incluirFrase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.frases.R
import com.example.frases.data.Phrase
import com.example.frases.databinding.ActivityIncluirFraseBinding
import com.example.frases.ui.main.MainActivity

class IncluirFraseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncluirFraseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncluirFraseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()

    }

    private fun setupListener() {
        setupBtnCancel()
        setupBtnSave()
    }

    private fun setupBtnSave() {
        binding.btnSave.setOnClickListener { savePhrase() }
    }

    private fun setupBtnCancel() {
        binding.btnCancel.setOnClickListener { finish() }
    }

    private fun savePhrase() {
        binding.apply {
            val author = editInputAuthor.text.toString()
            val phrase = editInputPhrase.text.toString()

            tilInputAuthor.error = if (author.isEmpty()) {
                getString(R.string.error_author)
            } else {
                null
            }

            tilInputPhrase.error = if(phrase.isEmpty()) {
                getString(R.string.error_phrase)
            } else {
                null
            }

            if(author.isNotEmpty() && phrase.isNotEmpty()) {
                Intent().apply {
                    putExtra(
                        MainActivity.RETURN_PHRASE,
                        Phrase(
                            author = author,
                            phrase = phrase
                        )
                    )
                    setResult(RESULT_OK, this)
                }
                finish()
            }
        }
    }


}