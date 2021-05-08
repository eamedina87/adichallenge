package tech.medina.adichallenge.data.api.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("imgUrl")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("reviews")
    val reviews: List<ReviewDto?>?
)