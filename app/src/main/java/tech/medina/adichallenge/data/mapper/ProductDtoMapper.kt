package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

class ProductDtoMapper @Inject constructor(
    private val reviewMapper: Mapper<Review, ReviewDto>
) : Mapper<Product, ProductDto> {
    override fun map(input: Product): ProductDto =
        ProductDto(
            id = input.id,
            reviews = input.reviews.map { reviewMapper.map(it) },
            currency = "",
            description = "",
            image = "",
            name = "",
            price = -1.0
        )
}