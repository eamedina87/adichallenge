package tech.medina.adichallenge.domain.models

data class Product(
    val id: String,
    val reviews: List<Review>
)