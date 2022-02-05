package com.vaslufi.castles.navigation

import kotlinx.coroutines.channels.ReceiveChannel

/**
 * The navigation dispatcher provides a decoupling between the nav host fragment and the viewmodels
 * that want to issue navigation commands. The navigator is the control or sink end of the
 * dispatch process that allows viewmodel classes to send commands to the dispatcher.
 */
interface Navigator {
    /**
     * Dispatch [navigation]. The navigation host will ultimately receive the navigation command.
     */
    suspend fun navigate(navigation: Navigation)
}

/**
 * An activity that hosts a navigation fragment should inject the navigation source, observe it
 * and execute commands that emanate from it.
 */
interface NavigationSource {
    /**
     * The source of navigation commands a host should observe.
     */
    val navigationCommands: ReceiveChannel<Navigation>
}
