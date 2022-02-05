package com.vaslufi.castles.ui.list.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.ui.list.CastleListLoaded
import com.vaslufi.castles.ui.list.CastleListViewModel
import com.vaslufi.castles.ui.list.CastleListViewState
import com.vaslufi.castles.ui.list.Error
import com.vaslufi.castles.ui.list.Loading
import com.vaslufi.castles.usecases.GetCastleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO Create unit tests
@HiltViewModel
class CastleListViewModelImpl @Inject constructor(
    private val getCastleListUseCase: GetCastleListUseCase,
) : ViewModel(), CastleListViewModel {
    override val viewState = MutableStateFlow<CastleListViewState>(Loading)
    override val startupJob: Job

    init {
        startupJob = viewModelScope.launch {
            loadCastleList()
        }
    }

    private suspend fun loadCastleList() {
        viewState.value = Loading
        viewState.value = when (val result = getCastleListUseCase.invoke()) {
            is Result.Success -> CastleListLoaded(result.value)
            is Result.Failure -> Error
        }
    }
}
