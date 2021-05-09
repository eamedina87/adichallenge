package tech.medina.adichallenge.domain.usecase

import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IReviewRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

interface IAddProductReviewUseCase {
    suspend operator fun invoke(productId: String, review: Review): DataState<Boolean>
}

class AddProductReviewUseCase @Inject constructor(
    private val repository: IReviewRepository,
    private val mapper: IMapper<Review, ReviewDto>
): IAddProductReviewUseCase {

    override suspend operator fun invoke(productId: String, review: Review): DataState<Boolean> =
        try {
            val reviewDto = mapper.map(review)
            val result = repository.addReviewForProductWithId(productId, reviewDto)
            DataState.Success(result == reviewDto)
        } catch (e: Exception) {
            DataState.Error(e)
        }

}