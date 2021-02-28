package com.vaslufi.castles.data.api

import com.squareup.moshi.Json

data class CastleListItemApiModel(
    @field:Json(name = "id")
    override val id: Long,
    @field:Json(name = "name")
    override val name: String,
    @field:Json(name = "imageUrl")
    override val imageUrl: String
) : BaseCastleApiModel
