package tech.medina.adichallenge.data.repository

import tech.medina.adichallenge.data.api.ProductApi
import tech.medina.adichallenge.data.api.dto.ProductDto
import javax.inject.Inject

interface IProductRepository {
    suspend fun getAllProducts(): List<ProductDto>
    suspend fun getProductById(id: String): ProductDto
}

class ProductRepository @Inject constructor(private val api: ProductApi): IProductRepository {

    override suspend fun getAllProducts(): List<ProductDto> =
        api.getAllProducts()

    override suspend fun getProductById(id: String): ProductDto =
        api.getProductById(id)

}