package com.vaslufi.castles.usecases

import com.vaslufi.castles.MockKUnitTest
import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.data.api.CastleDataApiModel
import com.vaslufi.castles.data.api.CoordinateApiModel
import com.vaslufi.castles.mapper.api.toview.CastleDataMapper
import com.vaslufi.castles.model.CastleData
import com.vaslufi.castles.model.CoordinateViewModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetCastleDetailsUseCaseImplTest : MockKUnitTest() {

    @MockK
    lateinit var castleService: CastleService

    @MockK
    lateinit var castleDataMapper: CastleDataMapper

    @InjectMockKs
    lateinit var tested: GetCastleDetailsUseCaseImpl

    @BeforeEach
    fun setup() {
        every { castleDataMapper.map(any<CastleDataApiModel>()) } returns mockCastleData
    }

    @Test
    fun `Should return castle details on success`() = runTest {
        coEvery { castleService.getCastleDetails(any()) } returns mockCastleApiData

        val result = tested.invoke(castleId = 1L)

        result.shouldBeInstanceOf<Result.Success<CastleData>>()
        result.value shouldBe mockCastleData
    }

    @Test
    fun `Should return failure on bad API response`() = runTest {
        coEvery { castleService.getCastleDetails(any()) } throws RuntimeException("")

        val result = tested.invoke(castleId = 1L)

        result.shouldBeInstanceOf<Result.Failure<*>>()
        result.cause.shouldBeInstanceOf<GetCastleDetailsUseCase.DetailsLoadException>()
    }

    private val mockCastleData = CastleData(
        id = 1L,
        name = "Warwick Castle",
        imageUrl = "https://image.url/1.jpg",
        description = "Warwick Castle is a medieval castle developed from a wooden fort...",
        coordinates = CoordinateViewModel(
            latitude = 52.279673567044476,
            longitude = -1.585226879165293,
        ),
        officialUrl = "https://www.warwick-castle.com/",
        googleCid = 2979592776009511813L,
    )

    private val mockCastleApiData = CastleDataApiModel(
        id = mockCastleData.id,
        name = mockCastleData.name,
        imageUrl = mockCastleData.imageUrl,
        description = mockCastleData.description,
        coordinates = CoordinateApiModel(
            latitude = mockCastleData.coordinates.latitude,
            longitude = mockCastleData.coordinates.longitude,
        ),
        officialUrl = mockCastleData.officialUrl,
        googleCid = mockCastleData.googleCid,
    )
}
