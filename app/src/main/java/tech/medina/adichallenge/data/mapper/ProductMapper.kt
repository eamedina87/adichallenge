package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val reviewMapper: IMapper<ReviewDto, Review>
) : IMapper<ProductDto, Product> {
    override fun map(input: ProductDto): Product =
        Product(
            id = input.id ?: "",
            reviews = input.reviews?.filterNotNull()?.map { reviewMapper.map(it) } ?: listOf()
        )
}