package com.vaslufi.castles.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CastleDetailsViewModel : ViewModel() {

    private val _viewState = MutableLiveData<CastleDetailsViewState>()
    val viewState: LiveData<CastleDetailsViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }

    fun loadCastleDetails(id: Long) {
        // TODO Load from API
        viewModelScope.launch {
            _viewState.value = Loading

            delay(500L)

            // TODO Get from repository
            _viewState.value = CastleDetailsLoaded(
                "Bodiam Castle"
            )
        }
    }
}
