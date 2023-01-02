package com.example.frases.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frases.data.Phrase
import com.example.frases.databinding.RvListItemBinding

class FrasesAdapter(
    private val phrases: List<Phrase>
) : RecyclerView.Adapter<FrasesAdapter.ViewHolder>() {

    private lateinit var binding: RvListItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = phrases[position])
    }

    override fun getItemCount(): Int = phrases.size


    inner

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Phrase) {
            binding.apply {
                txtAuthorName.text = item.author
                txtAuthorPhrase.text = item.phrase
            }
        }

    }

}