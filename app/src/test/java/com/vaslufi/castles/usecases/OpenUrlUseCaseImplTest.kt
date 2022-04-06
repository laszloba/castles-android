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
class OpenUrlUseCaseImplTest : MockKUnitTest() {

    @MockK
    lateinit var platformService: PlatformService

    @InjectMockKs
    lateinit var tested: OpenUrlUseCaseImpl

    @Test
    fun `Should invoke platform service with proper parameter`() = runTest {
        every { platformService.openUrl(any()) } returns Result.Success(Unit)

        tested.invoke("http://sample.url")

        verify {
            platformService.openUrl("http://sample.url")
        }
    }
}
