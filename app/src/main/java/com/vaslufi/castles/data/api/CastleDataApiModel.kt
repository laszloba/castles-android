package com.vaslufi.castles.data.api

import com.squareup.moshi.Json

data class CastleDataApiModel(
    @field:Json(name = "id")
    override val id: Long,
    @field:Json(name = "name")
    override val name: String,
    @field:Json(name = "imageUrl")
    override val imageUrl: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "coordinates")
    val coordinates: CoordinateApiModel,
    @field:Json(name = "officialUrl")
    val officialUrl: String?,
    @field:Json(name = "googleCid")
    val googleCid: Long?
) : BaseCastleApiModel
