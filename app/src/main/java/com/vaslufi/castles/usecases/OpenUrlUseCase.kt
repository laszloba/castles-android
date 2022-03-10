package com.vaslufi.castles.usecases

import com.vaslufi.castles.common.Result
import com.vaslufi.castles.service.PlatformService
import javax.inject.Inject

/**
 * Opens a URL in the default browser.
 */
interface OpenUrlUseCase {
    /**
     * Run this use case.
     */
    fun invoke(url: String): Result<Unit>
}
// TODO Create unit tests
class OpenUrlUseCaseImpl @Inject constructor(
    private val platformService: PlatformService,
) : OpenUrlUseCase {
    override fun invoke(url: String) = platformService.openUrl(url)
}
