package com.vaslufi.castles.ui.list

import com.vaslufi.castles.model.CastleListItemViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

interface CastleListViewModel {
    /**
     * Whether the list of the castles are loading.
     */
    val loading: StateFlow<Boolean>

    /**
     * The list of the castles. Null while loading or in error.
     */
    val castleList: StateFlow<List<CastleListItemViewModel>?>

    /**
     * The job started upon creating the view model, used for unit testing.
     */
    val startupJob: Job

    /**
     * Open the castle details screen.
     */
    fun openDetails(castleId: Long): Job
}
