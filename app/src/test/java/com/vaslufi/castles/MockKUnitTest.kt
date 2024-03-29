package com.vaslufi.castles

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 * Unit test base class for JUnit-flavored tests. Automatically initialize MockK annotations before tests.
 */
@ExperimentalCoroutinesApi
open class MockKUnitTest {

    private val mainTestCoroutineDispatcher by lazy { StandardTestDispatcher() }

    @BeforeEach
    fun setupDispatcher() {
        Dispatchers.setMain(mainTestCoroutineDispatcher)
    }

    @BeforeEach
    fun initMockKAnnotations() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
