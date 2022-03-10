package com.vaslufi.castles.ui.details

import com.vaslufi.castles.model.CastleData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

interface CastleDetailsViewModel {
    /**
     * Whether the details of the castle are loading.
     */
    val loading: StateFlow<Boolean>

    /**
     * The details of the castle. Null while loading or in error.
     */
    val details: StateFlow<CastleData?>

    /**
     * The job started upon creating the view model, used for unit testing.
     */
    val startupJob: Job

    /**
     * Open the official website of the castle.
     */
    fun openWebsite(): Job

    /**
     * Open the castle location on a map application.
     */
    fun openInMaps(): Job
}
