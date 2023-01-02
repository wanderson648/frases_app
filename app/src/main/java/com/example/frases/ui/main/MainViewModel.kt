package com.example.frases.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frases.data.Phrase
import com.example.frases.data.repository.MemoryRepository

class MainViewModel: ViewModel() {
    private var memoryRepository: MemoryRepository = MemoryRepository(mutableListOf())
    private val _listOfPhrases = MutableLiveData<List<Phrase>>()

    fun startData() {
        _listOfPhrases.value = memoryRepository.showList()
    }

    fun savePhrases(phrase: Phrase) {
        Log.i("IGTIinfo", "Frase recebida: $phrase")

        memoryRepository.save(phrase)
        updateListOfPhrase()
    }

    fun clearListOfPhrases() {
        Log.i("IGTIinfo", "Limpando lista de frases.")
        memoryRepository.clearList()
        updateListOfPhrase()
    }

    private fun updateListOfPhrase() {
        _listOfPhrases.value = memoryRepository.showList()
    }

}