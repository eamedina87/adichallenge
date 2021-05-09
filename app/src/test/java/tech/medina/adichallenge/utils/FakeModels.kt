package tech.medina.adichallenge.utils

import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review

object FakeModels {

    val review = Review(id = "")
    val product = Product(currency = "", description = "", id = "", imageUrl = "", name = "", price = "", reviews = listOf(review))
    
}