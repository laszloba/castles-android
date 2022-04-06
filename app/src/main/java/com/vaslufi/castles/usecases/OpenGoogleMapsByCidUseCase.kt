package com.vaslufi.castles.usecases

import com.vaslufi.castles.common.Result
import com.vaslufi.castles.service.PlatformService
import javax.inject.Inject

/**
 * Opens a location on Google Maps by Customer ID Number.
 */
interface OpenGoogleMapsByCidUseCase {
    /**
     * Run this use case.
     */
    fun invoke(googleCid: Long): Result<Unit>
}

class OpenGoogleMapsByCidUseCaseImpl @Inject constructor(
    private val platformService: PlatformService,
) : OpenGoogleMapsByCidUseCase {
    override fun invoke(googleCid: Long) = platformService.openUrl("https://maps.google.com/?cid=$googleCid")
}
