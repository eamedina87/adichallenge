package tech.medina.adichallenge.utils

import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review

object FakeModels {

    val review = Review(id = "ABCD", locale = "es", rating = 3, text = "the message")
    fun reviewList(): List<Review> {
        val list = arrayListOf<Review>()
        repeat(3) {
            list.add(review)
        }
        return list
    }
    val product = Product(currency = "$", description = "the description", id = "ABCD", imageUrl = "theurl", name = "PRODUCT NAME", price = 60.0)
    fun productList(): List<Product> {
        val list = arrayListOf<Product>()
        repeat(3) {
            list.add(product)
        }
        return list
    }

}