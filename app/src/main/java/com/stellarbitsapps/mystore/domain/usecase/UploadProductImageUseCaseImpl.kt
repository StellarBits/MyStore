package com.stellarbitsapps.mystore.domain.usecase

import android.net.Uri
import com.stellarbitsapps.mystore.data.ProductRepository

class UploadProductImageUseCaseImpl(
    private val productRepository: ProductRepository
) : UploadProductImageUseCase {

    override suspend fun invoke(imageUri: Uri): String {
        return productRepository.uploadProductImage(imageUri)
    }
}