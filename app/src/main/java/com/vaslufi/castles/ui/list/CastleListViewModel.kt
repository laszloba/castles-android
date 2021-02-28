package com.vaslufi.castles.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.mapper.api.toview.CastleListItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastleListViewModel @Inject constructor(
    private val service: CastleService,
    private val castleListItemMapper: CastleListItemMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<CastleListViewState>()
    val viewState: LiveData<CastleListViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }

    fun loadCastleList() {
        viewModelScope.launch {
            _viewState.value = Loading

            try {
                val castleListResponse = service.getCastleList()

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
