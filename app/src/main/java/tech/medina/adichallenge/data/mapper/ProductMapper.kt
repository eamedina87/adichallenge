package tech.medina.adichallenge.data.mapper

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import tech.medina.adichallenge.R
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import javax.inject.Inject

class ProductMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val reviewMapper: IMapper<ReviewDto, Review>
) : IMapper<ProductDto, Product> {
    override fun map(input: ProductDto): Product =
        Product(
            currency = if (input.currency.isNullOrBlank())  context.getString(R.string.currency_euro) else input.currency,
            description = input.description ?: "",
            id = input.id ?: "",
            imageUrl = input.image ?: "",
            name = input.name ?: "",
            price = input.price ?: "",
            reviews = input.reviews?.filterNotNull()?.map { reviewMapper.map(it) } ?: listOf()
        )
}