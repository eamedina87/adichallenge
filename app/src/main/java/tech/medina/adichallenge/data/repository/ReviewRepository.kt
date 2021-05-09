package tech.medina.adichallenge.data.repository

import tech.medina.adichallenge.data.api.ReviewApi
import tech.medina.adichallenge.data.api.dto.ReviewDto
import javax.inject.Inject

interface IReviewRepository {
    suspend fun getReviewsForProductWithId(productId: String): List<ReviewDto>
    suspend fun addReviewForProductWithId(productId: String, review: ReviewDto): ReviewDto
}

class ReviewRepository @Inject constructor(private val api: ReviewApi): IReviewRepository {

    override suspend fun getReviewsForProductWithId(productId: String): List<ReviewDto> =
        api.getAllReviewsForProductWithId()

    override suspend fun addReviewForProductWithId(productId: String, review: ReviewDto): ReviewDto =
        api.postReviewForProductWithId(productId, review)

}