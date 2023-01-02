package com.example.frases.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Phrase(
    var author: String,
    var phrase: String
): Parcelable
