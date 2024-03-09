package com.stellarbitsapps.mystore.domain.usecase

import com.stellarbitsapps.mystore.data.ProductRepository
import com.stellarbitsapps.mystore.domain.model.Product

class GetProductsUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductsUseCase {

    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}