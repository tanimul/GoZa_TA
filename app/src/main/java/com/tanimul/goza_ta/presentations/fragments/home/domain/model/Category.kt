package com.tanimul.goza_ta.presentations.fragments.home.domain.model

import androidx.annotation.Keep

@Keep
data class Category(
    val id: Int,
    val title: String,
    val image: Int
)