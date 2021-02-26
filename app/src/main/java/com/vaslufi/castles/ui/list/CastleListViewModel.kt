package com.vaslufi.castles.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.model.CastleListItemViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CastleListViewModel : ViewModel() {

    private val _viewState = MutableLiveData<CastleListViewState>()
    val viewState: LiveData<CastleListViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }

    fun loadCastleList() {
        // TODO Load from API
        viewModelScope.launch {
            _viewState.value = Loading

            delay(500L)

            // TODO Get from repository
            _viewState.value = CastleListLoaded(
                listOf(
                    CastleListItemViewModel(1, "Arundel Castle"),
                    CastleListItemViewModel(2, "Bodiam Castle"),
                    CastleListItemViewModel(3, "Hever Castle"),
                    CastleListItemViewModel(4, "Warwick Castle")
                )
            )
        }
    }
}
