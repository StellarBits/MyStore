package com.stellarbitsapps.mystore.domain.usecase

import com.stellarbitsapps.mystore.domain.model.Product

interface GetProductsUseCase {
    suspend operator fun invoke(): List<Product>
}