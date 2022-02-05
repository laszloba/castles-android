package com.vaslufi.castles.usecases

import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.mapper.api.toview.CastleListItemMapper
import com.vaslufi.castles.model.CastleListItemViewModel
import javax.inject.Inject

/**
 * Get the list of the castles.
 */
interface GetCastleListUseCase {
    /**
     * Run this use case.
     */
    suspend fun invoke(): Result<List<CastleListItemViewModel>>

    /**
     * Thrown if the castle list can not be loaded.
     */
    class ListLoadException : Exception()
}

// TODO Create unit tests
class GetCastleListUseCaseImpl @Inject constructor(
    private val service: CastleService,
    private val castleListItemMapper: CastleListItemMapper,
) : GetCastleListUseCase {
    override suspend fun invoke(): Result<List<CastleListItemViewModel>> {
        try {
            val castleListResponse = service.getCastleList()

            if (castleListResponse.isSuccessful) {
                castleListResponse.body()?.let {
                    return Result.Success(castleListItemMapper.map(it))
                }
            }
        } catch (e: Exception) {
            // TODO Add Timber logging here
            // TODO Handle connection errors separately
        }
        return Result.Failure(GetCastleListUseCase.ListLoadException())
    }
}
