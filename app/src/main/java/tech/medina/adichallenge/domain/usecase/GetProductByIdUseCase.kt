package tech.medina.adichallenge.domain.usecase

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import javax.inject.Inject

interface IGetProductByIdUseCase {
    suspend operator fun invoke(id: String): DataState<Product>
}

class GetProductByIdUseCase @Inject constructor(
    private val repository: IProductRepository,
    private val mapper: IMapper<ProductDto, Product>
): IGetProductByIdUseCase {

    override suspend operator fun invoke(id: String): DataState<Product> =
        try {
            val result = repository.getProductById(id)
            DataState.Success(mapper.map(result))
        } catch (e: Exception) {
            DataState.Error(e)
        }

}