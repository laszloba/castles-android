package com.vaslufi.castles.model

data class CastleData(
    override val id: Long,
    override val name: String,
    override val imageUrl: String,
    val description: String,
    val coordinates: CoordinateViewModel,
    val officialUrl: String?,
    val googleCid: Long?
) : BaseCastleViewModel
