package com.vaslufi.castles.usecases.navigation

import com.vaslufi.castles.navigation.ActionNavigation
import com.vaslufi.castles.navigation.Navigator
import com.vaslufi.castles.ui.list.CastleListFragmentDirections
import javax.inject.Inject

/**
 * Open the castle details screen.
 */
interface GoToCastleDetailsUseCase {
    /**
     * Run this use case.
     */
    suspend fun invoke(castleId: Long)
}

// TODO Create unit tests
class GoToCastleDetailsUseCaseImpl @Inject constructor(
    private val navigator: Navigator,
) : GoToCastleDetailsUseCase {
    override suspend fun invoke(castleId: Long) {
        navigator.navigate(
            ActionNavigation(
                CastleListFragmentDirections.openCastleDetailsFragment(
                    castleId = castleId
                ),
            ),
        )
    }
}
