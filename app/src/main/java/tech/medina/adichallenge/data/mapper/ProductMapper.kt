package tech.medina.adichallenge.data.mapper

import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.domain.models.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : IMapper<ProductEntity, Product> {
    override fun map(input: ProductEntity): Product =
        Product(
            currency = input.currency ?: "",
            description = input.description,
            id = input.remoteId,
            imageUrl = input.image ?: "",
            name = input.name,
            price = input.price ?: -1.0,
        )
}