package com.vaslufi.castles.navigator

import android.app.Activity
import androidx.navigation.Navigation
import com.vaslufi.castles.R
import com.vaslufi.castles.ui.list.CastleListFragmentDirections
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val activity: Activity
) : AppNavigator {

    private val navController by lazy {
        Navigation.findNavController(activity, R.id.fragmentNavHost)
    }

    override fun navigateToCastleDetails(id: Long) {
        navController.navigate(
            CastleListFragmentDirections
                .actionCastleListFragmentToCastleDetailsFragment(id)
        )
    }
}
