package com.vaslufi.castles.data.api

interface BaseCastleApiModel : BaseApiModel {
    val id: Long
    val name: String
    val imageUrl: String
}
