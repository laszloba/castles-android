package com.vaslufi.castles.usecases

import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.mapper.api.toview.CastleDataMapper
import com.vaslufi.castles.model.CastleDataViewModel
import javax.inject.Inject

/**
 * Get the details of a castle.
 */
interface GetCastleDetailsUseCase {
    /**
     * Run this use case.
     */
    suspend fun invoke(castleId: Long): Result<CastleDataViewModel>

    /**
     * Thrown if the castle can not be loaded.
     */
    class DetailsLoadException : Exception()
}

// TODO Create unit tests
class GetCastleDetailsUseCaseImpl @Inject constructor(
    private val service: CastleService,
    private val castleDataMapper: CastleDataMapper,
) : GetCastleDetailsUseCase {
    override suspend fun invoke(castleId: Long): Result<CastleDataViewModel> {
        try {
            val castleDetailsResponse = service.getCastleDetails(castleId)

            if (castleDetailsResponse.isSuccessful) {
                castleDetailsResponse.body()?.let {
                    return Result.Success(castleDataMapper.map(it))
                }
            }
        } catch (e: Exception) {
            // TODO Add Timber logging here
            // TODO Handle connection errors separately
        }
        return Result.Failure(GetCastleDetailsUseCase.DetailsLoadException())
    }
}
