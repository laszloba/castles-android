package com.vaslufi.castles.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.vaslufi.castles.databinding.FragmentCastleDetailsBinding
import com.vaslufi.castles.extension.exhaustive
import com.vaslufi.castles.ui.details.impl.CastleDetailsViewModelImpl
import com.vaslufi.castles.util.Intents
import com.vaslufi.castles.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastleDetailsFragment : Fragment() {

    private var _binding: FragmentCastleDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentCastleDetailsBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(CastleDetailsViewModelImpl::class.java)

        viewModel.viewState.collectIn(lifecycleScope, ::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: CastleDetailsViewState) {
        @Suppress("IMPLICIT_CAST_TO_ANY")
        when (viewState) {
            Loading -> {
                with(binding) {
                    loadingProgressBar.visibility = View.VISIBLE
                    contentLayout.visibility = View.INVISIBLE
                }
            }
            Error -> {
                with(binding) {
                    loadingProgressBar.visibility = View.INVISIBLE
                    contentLayout.visibility = View.INVISIBLE
                }

                Snackbar.make(
                    binding.rootLayout,
                    // TODO Show proper error message
                    "Error happened",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            is CastleDetailsLoaded -> {
                val castle = viewState.castle

                with(binding) {
                    loadingProgressBar.visibility = View.INVISIBLE
                    contentLayout.visibility = View.VISIBLE

                    Glide.with(requireActivity())
                        .load(castle.imageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageImageView)

                    nameTextView.text = castle.name
                    descriptionTextView.text = castle.description

                    showButtonWithUrl(openWebsiteButton, castle.officialUrl)
                    showButtonWithUrl(
                        openInGoogleMapsButton,
                        castle.googleCid?.let { "https://maps.google.com/?cid=$it" }
                    )
                }
            }
        }.exhaustive
    }

    private fun showButtonWithUrl(button: Button, url: String?) {
        with(button) {
            if (url != null) {
                visibility = View.VISIBLE
                setOnClickListener {
                    Intents.openUrl(context, url)
                }
            } else {
                visibility = View.GONE
            }
        }
    }
}
