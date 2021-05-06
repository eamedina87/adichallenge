package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

class ReviewDtoMapper @Inject constructor() : IMapper<Review, ReviewDto> {
    override fun map(input: Review): ReviewDto =
        ReviewDto(
            id = input.id,
            locale = "",
            rating = -1,
            text = ""
        )
}