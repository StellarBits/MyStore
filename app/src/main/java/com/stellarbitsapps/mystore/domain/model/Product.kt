package com.stellarbitsapps.mystore.domain.model

data class Product(
    val id: Int,
    val description: String = "",
    val price: Double = 0.0,
    val imageUrl: String = ""
)
