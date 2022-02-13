package com.vaslufi.castles.service

import com.vaslufi.castles.common.Result

/**
 * A service that handles the Android system specific actions.
 */
interface PlatformService {

    /**
     * Open the given URL in the device's default browser.
     *
     * @param url The URL that needs to be opened.
     */
    fun openUrl(url: String): Result<Unit>
}
