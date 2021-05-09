package tech.medina.adichallenge.data.api

import retrofit2.http.*
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.api.dto.ReviewDto

interface ReviewApi {

    @GET("reviews/{productId}")
    suspend fun getAllReviewsForProductWithId(): List<ReviewDto>

    @POST("reviews/{productId}")
    suspend fun postReviewForProductWithId(@Path("productId")id: String, @Body review: ReviewDto): ReviewDto

}