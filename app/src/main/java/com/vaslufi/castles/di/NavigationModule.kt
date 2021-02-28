package com.vaslufi.castles.di

import com.vaslufi.castles.navigator.AppNavigator
import com.vaslufi.castles.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(navigator: AppNavigatorImpl): AppNavigator
}
