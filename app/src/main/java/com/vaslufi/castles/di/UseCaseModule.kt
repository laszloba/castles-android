package com.vaslufi.castles.di

import com.vaslufi.castles.usecases.GetCastleDetailsUseCase
import com.vaslufi.castles.usecases.GetCastleDetailsUseCaseImpl
import com.vaslufi.castles.usecases.GetCastleListUseCase
import com.vaslufi.castles.usecases.GetCastleListUseCaseImpl
import com.vaslufi.castles.usecases.OpenGoogleMapsByCidUseCase
import com.vaslufi.castles.usecases.OpenGoogleMapsByCidUseCaseImpl
import com.vaslufi.castles.usecases.OpenUrlUseCase
import com.vaslufi.castles.usecases.OpenUrlUseCaseImpl
import com.vaslufi.castles.usecases.navigation.GoToCastleDetailsUseCase
import com.vaslufi.castles.usecases.navigation.GoToCastleDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetCastleListUseCase(implementation: GetCastleListUseCaseImpl): GetCastleListUseCase

    @Binds
    abstract fun bindGetCastleDetailsUseCase(implementation: GetCastleDetailsUseCaseImpl): GetCastleDetailsUseCase

    @Binds
    abstract fun bindGoToCastleDetailsUseCase(implementation: GoToCastleDetailsUseCaseImpl): GoToCastleDetailsUseCase

    @Binds
    abstract fun bindOpenUrlUseCase(implementation: OpenUrlUseCaseImpl): OpenUrlUseCase

    @Binds
    abstract fun bindOpenGoogleMapsByCidUseCase(implementation: OpenGoogleMapsByCidUseCaseImpl): OpenGoogleMapsByCidUseCase
}
