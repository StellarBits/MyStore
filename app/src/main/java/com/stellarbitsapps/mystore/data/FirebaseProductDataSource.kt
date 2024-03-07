package com.stellarbitsapps.mystore.data

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.stellarbitsapps.mystore.BuildConfig
import com.stellarbitsapps.mystore.domain.model.Product
import com.stellarbitsapps.mystore.util.COLLECTION_PRODUCTS
import com.stellarbitsapps.mystore.util.COLLECTION_ROOT
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource(
    firebaseFirestore: FirebaseFirestore,
    firebaseStorage: FirebaseStorage
) : ProductDataSource {

    private val documentReference =
        firebaseFirestore.document("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/")

    private val storageReference = firebaseStorage.reference

    override suspend fun getProducts(): List<Product> {
        return suspendCoroutine { continuation ->
            val productsReference = documentReference.collection(COLLECTION_PRODUCTS)

            productsReference.get().addOnSuccessListener { documents ->

                val products = mutableListOf<Product>()

                documents.forEach {
                    it.toObject(Product::class.java).run {
                        products.add(this)
                    }
                }

                continuation.resumeWith(Result.success(products))
            }

            productsReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }

    override suspend fun uploadProductImage(imageUri: Uri): String {
        TODO("Not yet implemented")
    }

    override suspend fun createProduct(product: Product): Product {
        TODO("Not yet implemented")
    }
}