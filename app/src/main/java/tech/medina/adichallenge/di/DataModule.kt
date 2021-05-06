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
import tech.medina.adichallenge.data.api.AdiChallengeApi
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
abstract class DataModuleProvides {

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
    fun providesApi(
        converter: Converter.Factory
    ): AdiChallengeApi {
        return Retrofit.Builder()
            .baseUrl("http://localhost:3001/")
            .addConverterFactory(converter)
            .build()
            .create(AdiChallengeApi::class.java)
    }

}