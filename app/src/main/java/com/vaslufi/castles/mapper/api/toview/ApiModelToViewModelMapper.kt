package com.vaslufi.castles.mapper.api.toview

import com.vaslufi.castles.data.api.BaseApiModel
import com.vaslufi.castles.mapper.BaseMapper
import com.vaslufi.castles.model.BaseViewModel

abstract class ApiModelToViewModelMapper<T : BaseApiModel, R : BaseViewModel> : BaseMapper<T, R>()
