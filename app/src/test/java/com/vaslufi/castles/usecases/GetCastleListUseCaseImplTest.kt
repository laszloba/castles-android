package com.vaslufi.castles.usecases

import com.vaslufi.castles.MockKUnitTest
import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.data.api.CastleListItemApiModel
import com.vaslufi.castles.mapper.api.toview.CastleListItemMapper
import com.vaslufi.castles.model.CastleListItemViewModel
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
class GetCastleListUseCaseImplTest : MockKUnitTest() {

    @MockK
    lateinit var castleService: CastleService

    @MockK
    lateinit var castleListItemMapper: CastleListItemMapper

    @InjectMockKs
    lateinit var tested: GetCastleListUseCaseImpl

    @BeforeEach
    fun setup() {
        every { castleListItemMapper.map(any<List<CastleListItemApiModel>>()) } returns mockCastlesList
    }

    @Test
    fun `Should return castle list on success`() = runTest {
        coEvery { castleService.getCastleList() } returns emptyList()

        val result = tested.invoke()

        result.shouldBeInstanceOf<Result.Success<List<CastleListItemViewModel>>>()
        result.value shouldBe mockCastlesList
    }

    @Test
    fun `Should return failure on bad API response`() = runTest {
        coEvery { castleService.getCastleList() } throws RuntimeException("")

        val result = tested.invoke()

        result.shouldBeInstanceOf<Result.Failure<*>>()
        result.cause.shouldBeInstanceOf<GetCastleListUseCase.ListLoadException>()
    }

    private val mockCastlesList = listOf(
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
