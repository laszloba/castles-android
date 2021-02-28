package com.vaslufi.castles.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaslufi.castles.api.CastleService
import com.vaslufi.castles.mapper.api.toview.CastleDataMapper
import kotlinx.coroutines.launch

class CastleDetailsViewModel : ViewModel() {

    private val _viewState = MutableLiveData<CastleDetailsViewState>()
    val viewState: LiveData<CastleDetailsViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }

    fun loadCastleDetails(id: Long) {
        viewModelScope.launch {
            // TODO Use dependency injection
            val castleService = CastleService.create()
            val castleDataMapper = CastleDataMapper()

            _viewState.value = Loading

            try {
                val castleDetailsResponse = castleService.getCastleDetails(id)

                if (castleDetailsResponse.isSuccessful) {
                    castleDetailsResponse.body()?.let {
                        _viewState.value =
                            CastleDetailsLoaded(
                                castleDataMapper.map(it)
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
