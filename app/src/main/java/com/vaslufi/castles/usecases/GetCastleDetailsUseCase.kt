package com.vaslufi.castles.usecases

import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.mapper.api.toview.CastleDataMapper
import com.vaslufi.castles.model.CastleData
import timber.log.Timber
import javax.inject.Inject

/**
 * Get the details of a castle.
 */
interface GetCastleDetailsUseCase {
    /**
     * Run this use case.
     */
    suspend fun invoke(castleId: Long): Result<CastleData>

    /**
     * Thrown if the castle can not be loaded.
     */
    class DetailsLoadException : Exception()
}

// TODO Create unit tests
class GetCastleDetailsUseCaseImpl @Inject constructor(
    private val castleService: CastleService,
    private val castleDataMapper: CastleDataMapper,
) : GetCastleDetailsUseCase {
    override suspend fun invoke(castleId: Long): Result<CastleData> {
        try {
            return Result.Success(
                castleDataMapper.map(
                    castleService.getCastleDetails(castleId)
                )
            )
        } catch (e: Exception) {
            Timber.w(e, "Failed to fetch the details of a castle.")
            // TODO Handle connection errors separately
        }
        return Result.Failure(GetCastleDetailsUseCase.DetailsLoadException())
    }
}
