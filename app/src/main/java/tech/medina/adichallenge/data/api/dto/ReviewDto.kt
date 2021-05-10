package tech.medina.adichallenge.data.api.dto

import com.google.gson.annotations.SerializedName

data class ReviewDto(
        @SerializedName("productId")
    val productId: String?,
        @SerializedName("locale")
    val locale: String?,
        @SerializedName("rating")
    val rating: Int?,
        @SerializedName("text")
    val text: String?
) {
    override fun equals(other: Any?): Boolean {
        if (other !is ReviewDto) return false
        return productId == other.productId &&
                rating == other.rating &&
                text == other.text
    }
}