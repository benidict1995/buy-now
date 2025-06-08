package com.benidict.buy_now.module

import android.content.Context
import com.benidict.buy_now.service.DataStoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalServiceModule {

    @Provides
    @Singleton
    fun provideDataStoreService(
        @ApplicationContext context: Context
    ): DataStoreService {
        return DataStoreService(context)
    }
}