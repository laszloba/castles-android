package com.vaslufi.castles.ui.details.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.ui.details.CastleDetailsFragmentArgs
import com.vaslufi.castles.ui.details.CastleDetailsLoaded
import com.vaslufi.castles.ui.details.CastleDetailsViewModel
import com.vaslufi.castles.ui.details.CastleDetailsViewState
import com.vaslufi.castles.ui.details.Error
import com.vaslufi.castles.ui.details.Loading
import com.vaslufi.castles.usecases.GetCastleDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO Create unit tests
@HiltViewModel
class CastleDetailsViewModelImpl @Inject constructor(
    private val getCastleDetailsUseCase: GetCastleDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), CastleDetailsViewModel {
    private val args by lazy { CastleDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle) }

    override val viewState = MutableStateFlow<CastleDetailsViewState>(Loading)
    override val startupJob: Job

    init {
        startupJob = viewModelScope.launch {
            loadCastleDetails(args.castleId)
        }
    }

    private suspend fun loadCastleDetails(id: Long) {
        viewState.value = Loading
        viewState.value = when (val result = getCastleDetailsUseCase.invoke(id)) {
            is Result.Success -> CastleDetailsLoaded(result.value)
            is Result.Failure -> Error
        }
    }
}
