package com.vaslufi.castles.di

import com.vaslufi.castles.api.CastleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCastleService(): CastleService =
        CastleService.create()
}
