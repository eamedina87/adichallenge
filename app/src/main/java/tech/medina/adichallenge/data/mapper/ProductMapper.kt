package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.domain.models.Product
import java.math.BigDecimal
import javax.inject.Inject

class ProductMapper @Inject constructor() : IMapper<ProductDto, Product> {
    override fun map(input: ProductDto): Product =
        Product(
            currency = input.currency ?: "",
            description = input.description ?: "",
            id = input.id ?: "",
            imageUrl = input.image ?: "",
            name = input.name ?: "",
            price = input.price ?: BigDecimal("-1"),
        )
}