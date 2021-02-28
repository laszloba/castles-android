package com.vaslufi.castles.data.api

import com.squareup.moshi.Json

data class CoordinateApiModel(
    @field:Json(name = "latitude")
    val latitude: Double,
    @field:Json(name = "longitude")
    val longitude: Double
) : BaseApiModel
