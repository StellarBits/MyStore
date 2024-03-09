package com.stellarbitsapps.mystore.domain.usecase

import android.net.Uri
import com.stellarbitsapps.mystore.data.ProductRepository
import com.stellarbitsapps.mystore.domain.model.Product
import java.lang.Exception
import java.util.UUID

class CreateProductUseCaseImpl(
    private val uploadProductImageUseCase: UploadProductImageUseCase,
    private val productRepository: ProductRepository
) : CreateProductUseCase {

    override suspend fun invoke(description: String, price: Double, imageUri: Uri): Product {
        return try {
            val imageUrl = uploadProductImageUseCase(imageUri)
            val product = Product(UUID.randomUUID().toString(), description, price, imageUrl)
            productRepository.createProduct(product)
        } catch (e: Exception) {
            throw e
        }
    }
}