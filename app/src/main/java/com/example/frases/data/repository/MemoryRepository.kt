package com.example.frases.data.repository

import com.example.frases.data.Phrase

class MemoryRepository(newList: MutableList<Phrase>) {
    private val listDb: MutableList<Phrase> = newList

    fun save(phrase: Phrase) {
        listDb.add(phrase)
    }

    fun clearList() = listDb.clear()

    fun showList() =  listDb.toList()

}