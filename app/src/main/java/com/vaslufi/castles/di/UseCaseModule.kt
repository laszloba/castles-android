package com.vaslufi.castles.di

import com.vaslufi.castles.usecases.GetCastleDetailsUseCase
import com.vaslufi.castles.usecases.GetCastleDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetCastleDetailsUseCase(implementation: GetCastleDetailsUseCaseImpl): GetCastleDetailsUseCase
}
