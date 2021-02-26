package com.vaslufi.castles.ui.list

import com.vaslufi.castles.model.CastleListItemViewModel

sealed class CastleListViewState

object Loading : CastleListViewState()

object Error : CastleListViewState()

data class CastleListLoaded(
    val castleList: List<CastleListItemViewModel>
) : CastleListViewState()
