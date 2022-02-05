package com.vaslufi.castles.util.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.vaslufi.castles.navigation.ActionNavigation
import com.vaslufi.castles.navigation.NavigationSource
import com.vaslufi.castles.navigation.ToResourceNavigation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Subscribe to and perform common navigation actions coming from [navigationSource]. [hostFragmentId] is needed to find the navigation
 * controller for this activity.
 */
fun AppCompatActivity.observeNavigationSource(
    navigationSource: NavigationSource,
    @IdRes hostFragmentId: Int,
) {
    lifecycleScope.launch {
        navigationSource.navigationCommands
            .receiveAsFlow()
            .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
            .collect { navigation ->
                Timber.d("Navigation action received: %s", navigation)
                when (navigation) {
                    is ToResourceNavigation -> findNavController(hostFragmentId).navigate(navigation.destination)
                    is ActionNavigation -> findNavController(hostFragmentId).navigate(navigation.action)
                }
            }
    }
}
