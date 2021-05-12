package tech.medina.adichallenge.utils

import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review

object FakeEntity {

    val product = ProductEntity(currency = "$", description = "the description", remoteId = "ABCD", image = "theurl", name = "PRODUCT NAME", price = 60.0)
    val productList = listOf(product)
    fun productList(): List<ProductEntity> {
        val list = arrayListOf<ProductEntity>()
        repeat(3) {
            list.add(product)
        }
        return list
    }

}