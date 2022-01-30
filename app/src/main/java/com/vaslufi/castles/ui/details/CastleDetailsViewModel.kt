package com.vaslufi.castles.ui.details

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface CastleDetailsViewModel {
    /**
     * The state of the current view.
     */
    val viewState: Flow<CastleDetailsViewState>

    /**
     * The job started upon creating the view model, used for unit testing.
     */
    val startupJob: Job
}
