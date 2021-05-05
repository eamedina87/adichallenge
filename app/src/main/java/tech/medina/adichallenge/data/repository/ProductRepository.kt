package tech.medina.adichallenge.data.repository

import tech.medina.adichallenge.data.api.AdiChallengeApi
import tech.medina.adichallenge.data.api.dto.ProductDto
import javax.inject.Inject

interface IProductRepository {
    suspend fun getAllProducts(): List<ProductDto>
    suspend fun getProductById(id: String): ProductDto
    suspend fun updateProduct(id: String, product: ProductDto): ProductDto
}

class ProductRepository @Inject constructor(private val api: AdiChallengeApi): IProductRepository {

    override suspend fun getAllProducts(): List<ProductDto> =
        api.getAllProducts()

    override suspend fun getProductById(id: String): ProductDto =
        api.getProductById(id)

    override suspend fun updateProduct(id: String, product: ProductDto): ProductDto =
        api.updateProductById(id, product)

}