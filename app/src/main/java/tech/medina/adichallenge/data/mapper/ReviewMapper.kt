package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor() : IMapper<ReviewDto, Review> {
    override fun map(input: ReviewDto): Review =
        Review(
            id = input.productId ?: "",
            locale = input.locale ?: "",
            rating = input.rating ?: -1,
            text = input.text ?: ""
        )
}