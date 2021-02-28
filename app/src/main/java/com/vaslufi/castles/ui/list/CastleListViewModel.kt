package com.vaslufi.castles.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.mapper.api.toview.CastleListItemMapper
import kotlinx.coroutines.launch

class CastleListViewModel : ViewModel() {

    private val _viewState = MutableLiveData<CastleListViewState>()
    val viewState: LiveData<CastleListViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }

    fun loadCastleList() {
        viewModelScope.launch {
            // TODO Use dependency injection
            val castleService = CastleService.create()
            val castleListItemMapper = CastleListItemMapper()

            _viewState.value = Loading

            try {
                val castleListResponse = castleService.getCastleList()

                if (castleListResponse.isSuccessful) {
                    castleListResponse.body()?.let {
                        _viewState.value =
                            CastleListLoaded(
                                castleListItemMapper.map(it)
                            )
                    }
                } else {
                    _viewState.value = Error
                }
            } catch (e: Exception) {
                _viewState.value = Error
            }
        }
    }
}
