package com.stellarbitsapps.mystore.data

import android.net.Uri
import com.stellarbitsapps.mystore.domain.model.Product

interface ProductDataSource {
    suspend fun getProducts(): List<Product>

    suspend fun uploadProductImage(imageUri: Uri): String

    suspend fun createProduct(product: Product): Product
}