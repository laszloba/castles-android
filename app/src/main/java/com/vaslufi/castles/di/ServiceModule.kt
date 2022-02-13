package com.vaslufi.castles.di

import android.content.Context
import com.vaslufi.castles.service.PlatformService
import com.vaslufi.castles.service.impl.PlatformServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providePlatformService(
        @ApplicationContext context: Context,
    ): PlatformService = PlatformServiceImpl(
        context = context,
    )
}
