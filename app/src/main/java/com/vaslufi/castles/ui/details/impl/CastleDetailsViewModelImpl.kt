package com.vaslufi.castles.ui.details.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.model.CastleData
import com.vaslufi.castles.ui.details.CastleDetailsFragmentArgs
import com.vaslufi.castles.ui.details.CastleDetailsViewModel
import com.vaslufi.castles.usecases.GetCastleDetailsUseCase
import com.vaslufi.castles.usecases.OpenGoogleMapsByCidUseCase
import com.vaslufi.castles.usecases.OpenUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastleDetailsViewModelImpl @Inject constructor(
    private val getCastleDetailsUseCase: GetCastleDetailsUseCase,
    private val openUrlUseCase: OpenUrlUseCase,
    private val openGoogleMapsByCidUseCase: OpenGoogleMapsByCidUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), CastleDetailsViewModel {
    private val args by lazy { CastleDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle) }

    override val loading = MutableStateFlow(true)
    override val details = MutableStateFlow<CastleData?>(null)
    override val startupJob: Job

    init {
        startupJob = viewModelScope.launch {
            loadCastleDetails(args.castleId)
        }
    }

    private suspend fun loadCastleDetails(id: Long) {
        loading.value = true
        when (val result = getCastleDetailsUseCase.invoke(id)) {
            is Result.Success -> details.value = result.value
            is Result.Failure -> {
                // TODO Show errors on UI
            }
        }
        loading.value = false
    }

    override fun openWebsite() = viewModelScope.launch {
        details.value?.officialUrl?.let { officialUrl ->
            // TODO Show errors on UI
            openUrlUseCase.invoke(officialUrl)
        }
    }

    override fun openInMaps() = viewModelScope.launch {
        details.value?.googleCid?.let { googleCid ->
            // TODO Show errors on UI
            openGoogleMapsByCidUseCase.invoke(googleCid)
        }
    }
}
