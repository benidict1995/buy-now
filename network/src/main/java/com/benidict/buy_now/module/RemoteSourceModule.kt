package com.benidict.buy_now.module

import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.source.banner.BannerRemoteSource
import com.benidict.buy_now.source.cart.CartRemoteSource
import com.benidict.buy_now.source.category.CategoryRemoteSource
import com.benidict.buy_now.source.notification.NotificationRemoteSource
import com.benidict.buy_now.source.product.ProductRemoteSource
import com.benidict.buy_now.source.user.UserRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideCartRemoteSource(firebaseService: FirebaseService) =
        CartRemoteSource(firebaseService)

    @Provides
    @Singleton
    fun provideUserRemoteSource(firebaseService: FirebaseService) =
        UserRemoteSource(firebaseService)

    @Provides
    @Singleton
    fun provideBannerRemoteSource(firebaseService: FirebaseService) =
        BannerRemoteSource(firebaseService)

    @Provides
    @Singleton
    fun provideProductRemoteSource(firebaseService: FirebaseService) =
        ProductRemoteSource(firebaseService)

    @Provides
    @Singleton
    fun provideCategoryRemoteSource(firebaseService: FirebaseService) =
        CategoryRemoteSource(firebaseService)

    @Provides
    @Singleton
    fun provideNotificationsRemoteSource(firebaseService: FirebaseService) =
        NotificationRemoteSource(firebaseService)
}