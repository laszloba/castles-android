package com.vaslufi.castles.mapper.api.toview

import com.vaslufi.castles.data.api.CastleDataApiModel
import com.vaslufi.castles.model.CastleDataViewModel
import com.vaslufi.castles.model.CoordinateViewModel
import javax.inject.Inject

class CastleDataMapper @Inject constructor() :
    ApiModelToViewModelMapper<CastleDataApiModel, CastleDataViewModel>() {

    override fun map(model: CastleDataApiModel): CastleDataViewModel =
        CastleDataViewModel(
            id = model.id,
            name = model.name,
            imageUrl = model.imageUrl,
            description = model.description,
            coordinates = CoordinateViewModel(
                latitude = model.coordinates.latitude,
                longitude = model.coordinates.longitude
            ),
            officialUrl = model.officialUrl,
            googleCid = model.googleCid
        )
}
