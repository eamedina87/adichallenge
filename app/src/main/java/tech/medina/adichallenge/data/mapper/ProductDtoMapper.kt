package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.domain.models.Product
import java.math.BigDecimal
import javax.inject.Inject

class ProductDtoMapper @Inject constructor() : IMapper<ProductDto, Product> {
    override fun map(input: ProductDto): Product =
        Product(
            id = input.id ?: "",
            currency = input.currency ?: "",
            description = input.description ?: "",
            imageUrl = input.image ?: "",
            name = input.name ?: "",
            price = input.price ?: -1.0
        )
}