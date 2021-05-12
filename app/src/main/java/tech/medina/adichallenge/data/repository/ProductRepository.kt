package tech.medina.adichallenge.data.repository

import tech.medina.adichallenge.data.api.ProductApi
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.db.AdiChallengeDatabase
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.mapper.IMapper
import javax.inject.Inject

interface IProductRepository {
    suspend fun getAllProducts(): List<ProductEntity>
    suspend fun getProductById(id: String): ProductDto
    suspend fun searchProducts(query: String): List<ProductEntity>
}

class ProductRepository @Inject constructor(
        private val api: ProductApi,
        private val database: AdiChallengeDatabase,
        private val mapper: IMapper<ProductDto, ProductEntity>
): IProductRepository {

    override suspend fun getAllProducts(): List<ProductEntity> {
        val apiProducts = api.getAllProducts()
        database.productDao().insert(apiProducts.map { mapper.map(it)})
        return database.productDao().getAll()
    }

    override suspend fun getProductById(id: String): ProductDto =
        api.getProductById(id)

    override suspend fun searchProducts(query: String): List<ProductEntity> =
        if (query.isBlank()) {
            database.productDao().getAll()
        } else {
            database.productDao().search(query)
        }

}