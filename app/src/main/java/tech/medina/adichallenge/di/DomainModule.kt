package tech.medina.adichallenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.medina.adichallenge.domain.usecase.*

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModuleBinds {

    @Binds
    abstract fun bindGetAllProductsUseCase(
        useCase: GetAllProductsUseCase
    ): IGetAllProductsUseCase

    @Binds
    abstract fun bindGetProductByIdUseCase(
        useCase: GetProductByIdUseCase
    ): IGetProductByIdUseCase

    @Binds
    abstract fun bindGetProductReviewsUseCase(
        useCase: GetProductReviewsUseCase
    ): IGetProductReviewsUseCase


    @Binds
    abstract fun bindAddProductReviewUseCase(
        useCase: AddProductReviewUseCase
    ): IAddProductReviewUseCase

}