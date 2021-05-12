package tech.medina.adichallenge.utils

import tech.medina.adichallenge.data.mapper.ProductMapper
import tech.medina.adichallenge.data.mapper.ReviewMapper
import tech.medina.adichallenge.domain.models.Product

object FakeRepo {

    private val productMapper = ProductMapper()

    val productList: List<Product> = FakeEntity.productList.map {
        productMapper.map(it)
    }
    val product: Product = productMapper.map(FakeEntity.product)

}