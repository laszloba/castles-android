package com.vaslufi.castles.ui.details.impl

import com.vaslufi.castles.MockKUnitTest
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.model.CastleData
import com.vaslufi.castles.model.CoordinateViewModel
import com.vaslufi.castles.ui.details.CastleDetailsFragmentArgs
import com.vaslufi.castles.ui.details.CastleDetailsViewModel
import com.vaslufi.castles.usecases.GetCastleDetailsUseCase
import com.vaslufi.castles.usecases.OpenGoogleMapsByCidUseCase
import com.vaslufi.castles.usecases.OpenUrlUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CastleDetailsViewModelImplTest : MockKUnitTest() {

    @MockK
    lateinit var getCastleDetailsUseCase: GetCastleDetailsUseCase

    @MockK
    lateinit var openUrlUseCase: OpenUrlUseCase

    @MockK
    lateinit var openGoogleMapsByCidUseCase: OpenGoogleMapsByCidUseCase

    @Test
    fun `Should load castle details on startup`() = runTest {
        coEvery { getCastleDetailsUseCase.invoke(any()) } returns Result.Success(mockCastleData)

        val tested = createSubject(castleId = 1234L)

        tested.startupJob.join()

        tested.details.value shouldBe mockCastleData

        coVerify {
            getCastleDetailsUseCase.invoke(castleId = 1234L)
        }
    }

    @Test
    fun `Should hide loading indicator on success`() = runTest {
        coEvery { getCastleDetailsUseCase.invoke(any()) } returns Result.Success(mockCastleData)

        val tested = createSubject()

        tested.startupJob.join()

        tested.loading.value shouldBe false
    }

    @Test
    fun `Should hide loading indicator on failure`() = runTest {
        coEvery { getCastleDetailsUseCase.invoke(any()) } returns Result.Failure(GetCastleDetailsUseCase.DetailsLoadException())

        val tested = createSubject()

        tested.startupJob.join()

        tested.loading.value shouldBe false
    }

    @Test
    fun `Should not return castle details on failure`() = runTest {
        coEvery { getCastleDetailsUseCase.invoke(any()) } returns Result.Failure(GetCastleDetailsUseCase.DetailsLoadException())

        val tested = createSubject()

        tested.startupJob.join()

        tested.details.value shouldBe null
    }

    @Test
    fun `Should open the website of the castle`() = runTest {
        coEvery { getCastleDetailsUseCase.invoke(any()) } returns Result.Success(mockCastleData)
        every { openUrlUseCase.invoke(any()) } returns Result.Success(Unit)

        val tested = createSubject()

        tested.startupJob.join()
        tested.openWebsite().join()

        verify {
            openUrlUseCase.invoke(requireNotNull(mockCastleData.officialUrl))
        }
    }

    @Test
    fun `Should open castle in Google Maps`() = runTest {
        coEvery { getCastleDetailsUseCase.invoke(any()) } returns Result.Success(mockCastleData)
        every { openGoogleMapsByCidUseCase.invoke(any()) } returns Result.Success(Unit)

        val tested = createSubject()

        tested.startupJob.join()
        tested.openInMaps().join()

        verify {
            openGoogleMapsByCidUseCase.invoke(requireNotNull(mockCastleData.googleCid))
        }
    }

    private fun createSubject(castleId: Long = 1L): CastleDetailsViewModel =
        CastleDetailsViewModelImpl(
            getCastleDetailsUseCase = getCastleDetailsUseCase,
            openUrlUseCase = openUrlUseCase,
            openGoogleMapsByCidUseCase = openGoogleMapsByCidUseCase,
            CastleDetailsFragmentArgs(castleId = castleId).toSavedStateHandle()
        )

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
}
