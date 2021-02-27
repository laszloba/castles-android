package com.vaslufi.castles.ui.details

sealed class CastleDetailsViewState

object Loading : CastleDetailsViewState()

object Error : CastleDetailsViewState()

data class CastleDetailsLoaded(
        val name: String
) : CastleDetailsViewState()
