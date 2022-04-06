package com.vaslufi.castles.ui.list.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.model.CastleListItemViewModel
import com.vaslufi.castles.ui.list.CastleListViewModel
import com.vaslufi.castles.usecases.GetCastleListUseCase
import com.vaslufi.castles.usecases.navigation.GoToCastleDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastleListViewModelImpl @Inject constructor(
    private val getCastleListUseCase: GetCastleListUseCase,
    private val goToCastleDetailsUseCase: GoToCastleDetailsUseCase,
) : ViewModel(), CastleListViewModel {
    override val loading = MutableStateFlow(true)
    override val castleList = MutableStateFlow<List<CastleListItemViewModel>?>(null)
    override val startupJob: Job

    init {
        startupJob = viewModelScope.launch {
            loadCastleList()
        }
    }

    private suspend fun loadCastleList() {
        loading.value = true
        when (val result = getCastleListUseCase.invoke()) {
            is Result.Success -> castleList.value = result.value
            is Result.Failure -> {
                // TODO Show errors on UI
            }
        }
        loading.value = false
    }

    override fun openDetails(castleId: Long) = viewModelScope.launch {
        goToCastleDetailsUseCase.invoke(castleId)
    }
}
