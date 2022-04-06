package com.vaslufi.castles.usecases

import com.vaslufi.castles.MockKUnitTest
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.service.PlatformService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class OpenGoogleMapsByCidUseCaseImplTest : MockKUnitTest() {

    @MockK
    lateinit var platformService: PlatformService

    @InjectMockKs
    lateinit var tested: OpenGoogleMapsByCidUseCaseImpl

    @Test
    fun `Should open proper URL`() = runTest {
        every { platformService.openUrl(any()) } returns Result.Success(Unit)

        tested.invoke(googleCid = 1234L)

        verify {
            platformService.openUrl("https://maps.google.com/?cid=1234")
        }
    }
}
