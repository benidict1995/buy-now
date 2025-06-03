package com.benidict.data.module

import com.benidict.buy_now.source.banner.BannerRemoteSource
import com.benidict.buy_now.source.category.CategoryRemoteSource
import com.benidict.buy_now.source.notification.NotificationRemoteSource
import com.benidict.buy_now.source.product.ProductRemoteSource
import com.benidict.data.repository.banner.BannerRepository
import com.benidict.data.repository.category.CategoryRepository
import com.benidict.data.repository.notifications.NotificationsRepository
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
    fun provideBannerRepository(bannerRemoteSource: BannerRemoteSource): BannerRepository {
        return BannerRepository(bannerRemoteSource)
    }

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

    @Provides
    @Singleton
    fun provideNotificationsRepository(notificationRemoteSource: NotificationRemoteSource): NotificationsRepository {
        return NotificationsRepository(notificationRemoteSource)
    }
}