package com.vaslufi.castles.ui.list.impl

import com.vaslufi.castles.MockKUnitTest
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.common.Result.Success
import com.vaslufi.castles.model.CastleListItemViewModel
import com.vaslufi.castles.ui.list.CastleListViewModel
import com.vaslufi.castles.usecases.GetCastleListUseCase
import com.vaslufi.castles.usecases.navigation.GoToCastleDetailsUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CastleListViewModelImplTest : MockKUnitTest() {

    @MockK
    lateinit var getCastleListUseCase: GetCastleListUseCase

    @MockK
    lateinit var goToCastleDetailsUseCase: GoToCastleDetailsUseCase

    @Test
    fun `Should load castle list on startup`() = runTest {
        coEvery { getCastleListUseCase.invoke() } returns Success(mockCasteList)

        val tested = createSubject()

        tested.startupJob.join()

        tested.castleList.value shouldBe mockCasteList
    }

    @Test
    fun `Should hide loading indicator on success`() = runTest {
        coEvery { getCastleListUseCase.invoke() } returns Success(mockCasteList)

        val tested = createSubject()

        tested.startupJob.join()

        tested.loading.value shouldBe false
    }

    @Test
    fun `Should hide loading indicator on failure`() = runTest {
        coEvery { getCastleListUseCase.invoke() } returns Result.Failure(GetCastleListUseCase.ListLoadException())

        val tested = createSubject()

        tested.startupJob.join()

        tested.loading.value shouldBe false
    }

    @Test
    fun `Should not return castle list on failure`() = runTest {
        coEvery { getCastleListUseCase.invoke() } returns Result.Failure(GetCastleListUseCase.ListLoadException())

        val tested = createSubject()

        tested.startupJob.join()

        tested.castleList.value shouldBe null
    }

    @Test
    fun `Should navigate to castle details`() = runTest {
        val tested = createSubject()

        tested.startupJob.join()

        tested.openDetails(castleId = 42L).join()

        coVerify {
            goToCastleDetailsUseCase.invoke(castleId = 42L)
        }
    }

    private fun createSubject(): CastleListViewModel =
        CastleListViewModelImpl(
            getCastleListUseCase = getCastleListUseCase,
            goToCastleDetailsUseCase = goToCastleDetailsUseCase,
        )

    private val mockCasteList = listOf(
        CastleListItemViewModel(
            id = 1L,
            name = "Arundel Castle",
            imageUrl = "https://image.url",
        ),
        CastleListItemViewModel(
            id = 2L,
            name = "Bodiam Castle",
            imageUrl = "https://image.url",
        ),
    )
}
