package tech.medina.adichallenge.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import tech.medina.adichallenge.data.api.dto.ProductDto

interface AdiChallengeApi {

    @GET("product")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("product/{productId}")
    suspend fun getProductById(@Path("productId")id: String): ProductDto

    @PUT("product/{productId}")
    suspend fun updateProductById(@Path("productId")id: String, @Body product: ProductDto): ProductDto


}