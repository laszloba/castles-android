package com.vaslufi.castles.model

interface BaseCastleViewModel : BaseViewModel {
    val id: Long
    val name: String
    val imageUrl: String
}
