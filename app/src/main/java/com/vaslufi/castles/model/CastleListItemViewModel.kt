package com.vaslufi.castles.model

data class CastleListItemViewModel(
    override val id: Long,
    override val name: String,
    override val imageUrl: String
) : BaseCastleViewModel
