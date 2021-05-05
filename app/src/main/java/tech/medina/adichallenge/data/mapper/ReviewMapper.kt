package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor() : Mapper<ReviewDto, Review> {
    override fun map(input: ReviewDto): Review =
        Review(
            id = input.id ?: ""
        )
}