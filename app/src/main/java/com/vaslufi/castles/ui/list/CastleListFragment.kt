package com.vaslufi.castles.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vaslufi.castles.ui.list.impl.CastleListViewModelImpl
import com.vaslufi.castles.ui.theme.CastlesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastleListFragment : Fragment() {
    private val viewModel by viewModels<CastleListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CastlesTheme {
                CastleList(
                    viewModel = viewModel,
                )
            }
        }
    }
}
