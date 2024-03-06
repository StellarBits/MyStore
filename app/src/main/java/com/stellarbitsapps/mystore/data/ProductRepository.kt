package com.stellarbitsapps.mystore.data

import android.net.Uri
import com.stellarbitsapps.mystore.domain.model.Product

class ProductRepository(private val dataSource: ProductDataSource) {
    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun uploadProductImage(imageUri: Uri): String = dataSource.uploadProductImage(imageUri)

    suspend fun createProduct(product: Product): Product = dataSource.createProduct(product)
}