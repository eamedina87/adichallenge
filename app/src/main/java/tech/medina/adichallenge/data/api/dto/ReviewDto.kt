package tech.medina.adichallenge.data.api.dto

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("locale")
    val locale: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("text")
    val text: String?
)