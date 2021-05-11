package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.domain.models.Product
import java.math.BigDecimal
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() : IMapper<ProductDto, ProductEntity> {
    override fun map(input: ProductDto): ProductEntity =
        ProductEntity(
            id = 0,
            currency = input.currency ?: "",
            description = input.description ?: "",
            image = input.image ?: "",
            name = input.name ?: "",
            price = input.price ?: -1.0,
            remoteId = input.id ?: ""
        )
}