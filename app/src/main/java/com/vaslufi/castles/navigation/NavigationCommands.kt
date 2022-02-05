package com.vaslufi.castles.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

sealed class Navigation

data class ToResourceNavigation(@IdRes val destination: Int) : Navigation()
data class ActionNavigation(val action: NavDirections) : Navigation()
