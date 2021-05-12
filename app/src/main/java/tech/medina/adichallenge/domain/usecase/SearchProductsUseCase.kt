package tech.medina.adichallenge.domain.usecase

import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import javax.inject.Inject

interface ISearchProductsUseCase {
    suspend operator fun invoke(query: String): DataState<List<Product>>
}

class SearchProductsUseCase @Inject constructor(
    private val repository: IProductRepository,
    private val mapper: IMapper<ProductEntity, Product>
): ISearchProductsUseCase {

    override suspend operator fun invoke(query: String): DataState<List<Product>> =
        try {
            DataState.Success(repository.searchProducts(query).map {
                mapper.map(it)
            })
        } catch (e: Exception) {
            DataState.Error(e)
        }

}