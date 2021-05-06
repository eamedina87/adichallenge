package tech.medina.adichallenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.data.mapper.*
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review

@InstallIn(SingletonComponent::class)
@Module
abstract class MapperModule {

    @Binds
    abstract fun bindProductDtoMapper(
        repository: ProductDtoMapper
    ): IMapper<Product, ProductDto>

    @Binds
    abstract fun bindProductMapper(
        repository: ProductMapper
    ): IMapper<ProductDto, Product>

    @Binds
    abstract fun bindReviewDtoMapper(
        repository: ReviewDtoMapper
    ): IMapper<Review, ReviewDto>

    @Binds
    abstract fun bindReviewMapper(
        repository: ReviewMapper
    ): IMapper<ReviewDto, Review>

}