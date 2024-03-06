package com.stellarbitsapps.mystore.domain.usecase

import android.net.Uri
import com.stellarbitsapps.mystore.domain.model.Product

interface CreateProductUseCase {
    suspend operator fun invoke(
        description: String,
        price: Double,
        imageUri: Uri
    ): Product
}