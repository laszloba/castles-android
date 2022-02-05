package com.vaslufi.castles.ui.list

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

interface CastleListViewModel {
    /**
     * The state of the current view.
     */
    val viewState: StateFlow<CastleListViewState>

    /**
     * The job started upon creating the view model, used for unit testing.
     */
    val startupJob: Job

    /**
     * Open castle details screen.
     */
    fun openDetails(castleId: Long): Job
}
