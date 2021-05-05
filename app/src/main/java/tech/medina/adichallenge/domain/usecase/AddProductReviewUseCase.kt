package tech.medina.adichallenge.domain.usecase

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.mapper.Mapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

interface IAddProductReviewUseCase {
    suspend operator fun invoke(productId: String, review: Review): DataState<Product>
}

class AddProductReviewUseCase @Inject constructor(
    private val repository: IProductRepository,
    private val mapper: Mapper<ProductDto, Product>
): IAddProductReviewUseCase {

    override suspend operator fun invoke(productId: String, review: Review): DataState<Product> =
        try {
            val result = repository.getProductById(productId)
            DataState.Success(mapper.map(result))
        } catch (e: Exception) {
            DataState.Error(e)
        }

}