package tech.medina.adichallenge.di

import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.medina.adichallenge.BuildConfig
import tech.medina.adichallenge.data.api.ProductApi
import tech.medina.adichallenge.data.api.ReviewApi
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.data.repository.ProductRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModuleBinds {

    @Binds
    abstract fun bindProductRepository(
        repository: ProductRepository
    ): IProductRepository


}

@InstallIn(SingletonComponent::class)
@Module
class DataModuleProvides {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun providesRetrofitConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesProductApi(
        converter: Converter.Factory
    ): ProductApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_PRODUCT_URL)
            .addConverterFactory(converter)
            .build()
            .create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun providesReviewApi(
        converter: Converter.Factory
    ): ReviewApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_REVIEW_URL)
            .addConverterFactory(converter)
            .build()
            .create(ReviewApi::class.java)
    }

}