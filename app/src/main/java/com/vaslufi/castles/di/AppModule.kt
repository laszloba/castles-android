package com.vaslufi.castles.di

import com.vaslufi.castles.navigation.NavigationDispatcherImpl
import com.vaslufi.castles.navigation.NavigationSource
import com.vaslufi.castles.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavigationDispatcher() = NavigationDispatcherImpl()

    @Provides
    fun provideNavigator(navigationDispatcherImpl: NavigationDispatcherImpl): Navigator = navigationDispatcherImpl

    @Provides
    fun provideNavigationSource(navigationDispatcherImpl: NavigationDispatcherImpl): NavigationSource = navigationDispatcherImpl
}
