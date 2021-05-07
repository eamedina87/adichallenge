package tech.medina.adichallenge.domain.usecase

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import javax.inject.Inject

interface IGetAllProductsUseCase {
    suspend operator fun invoke(): DataState<List<Product>>
}

class GetAllProductsUseCase @Inject constructor(
    private val repository: IProductRepository,
    private val mapper: IMapper<ProductDto, Product>
): IGetAllProductsUseCase {

    override suspend operator fun invoke(): DataState<List<Product>> =
        try {
            DataState.Success(repository.getAllProducts().map {
                mapper.map(it)
            })
        } catch (e: Exception) {
            DataState.Error(e)
        }

}