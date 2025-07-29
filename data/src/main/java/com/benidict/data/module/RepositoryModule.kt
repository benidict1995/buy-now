package com.benidict.data.module

import com.benidict.buy_now.source.banner.BannerRemoteSource
import com.benidict.buy_now.source.cart.CartRemoteSource
import com.benidict.buy_now.source.category.CategoryRemoteSource
import com.benidict.buy_now.source.notification.NotificationRemoteSource
import com.benidict.buy_now.source.product.ProductRemoteSource
import com.benidict.buy_now.source.user.UserLocalSource
import com.benidict.buy_now.source.user.UserRemoteSource
import com.benidict.data.repository.banner.BannerRepository
import com.benidict.data.repository.cart.CartRepository
import com.benidict.data.repository.category.CategoryRepository
import com.benidict.data.repository.notifications.NotificationsRepository
import com.benidict.data.repository.product.ProductRepository
import com.benidict.data.repository.user.UserRepository
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
    fun provideCartRepository(userLocalSource: UserLocalSource, cartRemoteSource: CartRemoteSource): CartRepository {
        return CartRepository(userLocalSource, cartRemoteSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteSource: UserRemoteSource, userLocalSource: UserLocalSource): UserRepository {
        return UserRepository(userRemoteSource, userLocalSource)
    }

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