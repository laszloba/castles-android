package com.vaslufi.castles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vaslufi.castles.R
import com.vaslufi.castles.navigation.NavigationSource
import com.vaslufi.castles.util.extension.observeNavigationSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationSource: NavigationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hookUpNavigationSource()
    }

    private fun hookUpNavigationSource() {
        observeNavigationSource(
            navigationSource = navigationSource,
            hostFragmentId = R.id.fragmentNavHost,
        )
    }
}
