package com.benidict.buy_now.module

import com.benidict.buy_now.service.DataStoreService
import com.benidict.buy_now.source.user.UserLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {

    @Singleton
    @Provides
    fun provideUserLocalSource(dataStoreService: DataStoreService): UserLocalSource {
        return UserLocalSource(dataStoreService)
    }
}