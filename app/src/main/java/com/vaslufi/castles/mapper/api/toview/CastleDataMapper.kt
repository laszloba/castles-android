package com.vaslufi.castles.mapper.api.toview

import com.vaslufi.castles.data.api.CastleDataApiModel
import com.vaslufi.castles.model.CastleData
import com.vaslufi.castles.model.CoordinateViewModel
import javax.inject.Inject

class CastleDataMapper @Inject constructor() :
    ApiModelToViewModelMapper<CastleDataApiModel, CastleData>() {

    override fun map(model: CastleDataApiModel): CastleData =
        CastleData(
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
