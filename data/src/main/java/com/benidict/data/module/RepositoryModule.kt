package com.benidict.data.module

import com.benidict.buy_now.source.category.CategoryRemoteSource
import com.benidict.buy_now.source.product.ProductRemoteSource
import com.benidict.data.repository.category.CategoryRepository
import com.benidict.data.repository.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(productRemoteSource: ProductRemoteSource): ProductRepository {
        return ProductRepository(productRemoteSource)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryRemoteSource: CategoryRemoteSource): CategoryRepository {
        return CategoryRepository(categoryRemoteSource)
    }
}