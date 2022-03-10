package com.vaslufi.castles.ui.details

import com.vaslufi.castles.model.CastleData

sealed class CastleDetailsViewState

object Loading : CastleDetailsViewState()

object Error : CastleDetailsViewState()

data class CastleDetailsLoaded(
    val castle: CastleData,
) : CastleDetailsViewState()
