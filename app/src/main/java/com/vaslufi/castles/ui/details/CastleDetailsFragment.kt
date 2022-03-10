package com.vaslufi.castles.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vaslufi.castles.ui.details.impl.CastleDetailsViewModelImpl
import com.vaslufi.castles.ui.theme.CastlesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastleDetailsFragment : Fragment() {
    private val viewModel by viewModels<CastleDetailsViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CastlesTheme {
                CastleDetails(
                    viewModel = viewModel,
                )
            }
        }
    }
}
