package tech.medina.adichallenge.domain.models

data class Product(
    val currency: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: String, //since we are not making math operations in this app, we'll leave it as String
    val reviews: List<Review>
)