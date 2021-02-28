package com.vaslufi.castles.mapper.api.toview

import com.vaslufi.castles.data.api.CastleListItemApiModel
import com.vaslufi.castles.model.CastleListItemViewModel

class CastleListItemMapper :
    ApiModelToViewModelMapper<CastleListItemApiModel, CastleListItemViewModel>() {

    override fun map(model: CastleListItemApiModel): CastleListItemViewModel =
        CastleListItemViewModel(
            id = model.id,
            name = model.name,
            imageUrl = model.imageUrl
        )
}
