package com.vaslufi.castles.usecases.navigation

import com.vaslufi.castles.MockKUnitTest
import com.vaslufi.castles.navigation.ActionNavigation
import com.vaslufi.castles.navigation.Navigator
import com.vaslufi.castles.ui.list.CastleListFragmentDirections
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GoToCastleDetailsUseCaseImplTest : MockKUnitTest() {

    @MockK
    lateinit var navigator: Navigator

    @InjectMockKs
    lateinit var tested: GoToCastleDetailsUseCaseImpl

    @Test
    fun `Should invoke navigator`() = runTest {
        tested.invoke(castleId = 1234L)

        coVerify {
            navigator.navigate(
                ActionNavigation(
                    CastleListFragmentDirections.openCastleDetailsFragment(
                        castleId = 1234L,
                    ),
                ),
            )
        }
    }
}
