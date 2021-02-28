package com.vaslufi.castles.api

import com.vaslufi.castles.data.api.CastleDataApiModel
import com.vaslufi.castles.data.api.CastleListItemApiModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CastleService {

    @GET("ee0bca579d49e3c9936344222ad6b7d8/raw/ed1306e3c40b5d8d7562e05b3783ea82a0892b65/castlelist.json")
    suspend fun getCastleList(): Response<List<CastleListItemApiModel>>

    @GET("ee0bca579d49e3c9936344222ad6b7d8/raw/ed1306e3c40b5d8d7562e05b3783ea82a0892b65/details{id}.json")
    suspend fun getCastleDetails(@Path("id") id: Long): Response<CastleDataApiModel>

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/laszloba/"

        fun create(): CastleService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(CastleService::class.java)
    }
}
