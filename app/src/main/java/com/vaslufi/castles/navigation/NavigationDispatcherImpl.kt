package com.vaslufi.castles.navigation

import kotlinx.coroutines.channels.Channel
import timber.log.Timber
import javax.inject.Inject

class NavigationDispatcherImpl @Inject constructor() : Navigator, NavigationSource {
    private val navigationChannel: Channel<Navigation> = Channel()
    override val navigationCommands get() = navigationChannel

    override suspend fun navigate(navigation: Navigation) {
        Timber.d("Submitting navigation action: %s", navigation)
        navigationChannel.send(navigation)
    }
}
