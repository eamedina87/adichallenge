package tech.medina.adichallenge.domain.usecase

import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IReviewRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

interface IGetProductReviewsUseCase {
    suspend operator fun invoke(productId: String): DataState<List<Review>>
}

class GetProductReviewsUseCase @Inject constructor(
    private val repository: IReviewRepository,
    private val mapper: IMapper<ReviewDto, Review>
): IGetProductReviewsUseCase {

    override suspend operator fun invoke(productId: String): DataState<List<Review>> =
        try {
            DataState.Success(repository.getReviewsForProductWithId(productId).map {
                mapper.map(it)
            })
        } catch (e: Exception) {
            DataState.Error(e)
        }

}