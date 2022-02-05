package com.vaslufi.castles.navigation

import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class NavigationDispatcherImpl @Inject constructor() : Navigator, NavigationSource {
    private val navigationChannel: Channel<Navigation> = Channel()
    override val navigationCommands get() = navigationChannel

    override suspend fun navigate(navigation: Navigation) {
        navigationChannel.send(navigation)
    }
}
