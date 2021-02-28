package com.vaslufi.castles.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vaslufi.castles.databinding.FragmentCastleDetailsBinding
import com.vaslufi.castles.extension.exhaustive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastleDetailsFragment : Fragment() {

    private var _binding: FragmentCastleDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: CastleDetailsFragmentArgs by navArgs()
    private val viewModel: CastleDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCastleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        viewModel.loadCastleDetails(args.castleId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: CastleDetailsViewState) {
        @Suppress("IMPLICIT_CAST_TO_ANY")
        when (viewState) {
            Loading -> {
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.nameTextView.visibility = View.INVISIBLE
            }
            Error -> {
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.nameTextView.visibility = View.INVISIBLE

                Snackbar.make(
                    binding.rootLayout,
                    // TODO Show proper error message
                    "Error happened",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            is CastleDetailsLoaded -> {
                binding.loadingProgressBar.visibility = View.INVISIBLE
                with(binding.nameTextView) {
                    visibility = View.VISIBLE
                    text = viewState.castle.name
                }
            }
        }.exhaustive
    }
}
