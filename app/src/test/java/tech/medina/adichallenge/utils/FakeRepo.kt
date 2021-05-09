package tech.medina.adichallenge.utils

import tech.medina.adichallenge.data.mapper.ProductMapper
import tech.medina.adichallenge.data.mapper.ReviewMapper
import tech.medina.adichallenge.domain.models.Product

object FakeRepo {

    private val productMapper = ProductMapper(reviewMapper = ReviewMapper(), )

    val productList: List<Product> = FakeApi.productList.map {
        productMapper.map(it)
    }
    val product: Product = productMapper.map(FakeApi.product)

}