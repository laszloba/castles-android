package com.vaslufi.castles.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vaslufi.castles.R
import com.vaslufi.castles.model.CastleData
import com.vaslufi.castles.model.CoordinateViewModel
import com.vaslufi.castles.ui.common.Loading
import com.vaslufi.castles.ui.theme.CastlesTheme

@Composable
fun CastleDetails(
    viewModel: CastleDetailsViewModel,
) {
    val loading by viewModel.loading.collectAsState()
    val details by viewModel.details.collectAsState()

    CastleDetails(
        loading = loading,
        details = details,
        onOpenWebsiteClick = viewModel::openWebsite,
        onOpenInMapsClick = viewModel::openInMaps,
    )
}

@Composable
private fun CastleDetails(
    loading: Boolean,
    details: CastleData?,
    onOpenWebsiteClick: () -> Unit,
    onOpenInMapsClick: () -> Unit,
) {
    Surface {
        if (loading) {
            Loading()
        } else {
            // TODO Show errors
            details?.let { castleDetails ->
                CastleDetails(
                    details = castleDetails,
                    onOpenWebsiteClick = onOpenWebsiteClick,
                    onOpenInMapsClick = onOpenInMapsClick,
                )
            }
        }
    }
}

@Composable
private fun CastleDetails(
    details: CastleData,
    onOpenWebsiteClick: () -> Unit,
    onOpenInMapsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberImagePainter(
                data = details.imageUrl,
                builder = {
                    // TODO Use a custom placeholder
                    placeholder(R.mipmap.ic_launcher)
                    crossfade(true)
                }
            ),
            contentDescription = stringResource(R.string.content_description_image_of_the_castle),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = details.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
        )
        Text(
            text = details.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            textAlign = TextAlign.Justify,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            details.officialUrl?.let { _ ->
                Button(onClick = onOpenWebsiteClick) {
                    Text(stringResource(R.string.castle_details_open_website_button))
                }
            }
            details.googleCid?.let { _ ->
                Button(onClick = onOpenInMapsClick) {
                    Text(stringResource(R.string.castle_details_open_in_google_maps_button))
                }
            }
        }
    }
}

@Preview(
    name = "Details loaded",
    group = "loaded",
    showSystemUi = true,
)
@Composable
private fun CastleDetailsPreview() {
    CastlesTheme {
        CastleDetails(
            loading = false,
            details = sampleCastleData,
            onOpenWebsiteClick = {},
            onOpenInMapsClick = {},
        )
    }
}

@Preview(
    name = "Loading",
    group = "loading",
    showSystemUi = true,
)
@Composable
private fun CastleDetailsLoadingPreview() {
    CastlesTheme {
        CastleDetails(
            loading = true,
            details = null,
            onOpenWebsiteClick = {},
            onOpenInMapsClick = {},
        )
    }
}

@Preview(
    name = "Website button only",
    group = "loaded",
    showSystemUi = true,
)
@Composable
private fun CastleDetailsWebsiteOnlyPreview() {
    CastlesTheme {
        CastleDetails(
            loading = false,
            details = sampleCastleData.copy(googleCid = null),
            onOpenWebsiteClick = {},
            onOpenInMapsClick = {},
        )
    }
}

@Preview(
    name = "Maps button only",
    group = "loaded",
    showSystemUi = true,
)
@Composable
private fun CastleDetailsMapsOnlyPreview() {
    CastlesTheme {
        CastleDetails(
            loading = false,
            details = sampleCastleData.copy(officialUrl = null),
            onOpenWebsiteClick = {},
            onOpenInMapsClick = {},
        )
    }
}

private val sampleCastleData = CastleData(
    id = 1,
    name = "Warwick Castle",
    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/ae/Warwick_Castle_May_2016.jpg",
    description = "Warwick Castle is a medieval castle developed from a wooden fort, originally built by William the Conqueror during " +
        "1068. Warwick is the county town of Warwickshire, England, situated on a meander of the River Avon. The original wooden m" +
        "otte-and-bailey castle was rebuilt in stone during the 12th century. During the Hundred Years War, the facade opposite th" +
        "e town was refortified, resulting in one of the most recognisable examples of 14th-century military architecture. It was " +
        "used as a stronghold until the early 17th century, when it was granted to Sir Fulke Greville by James I in 1604. Greville" +
        " converted it to a country house, and it was owned by the Greville family (who became Earls of Warwick in 1759) until 197" +
        "8, when it was bought by the Tussauds Group.",
    coordinates = CoordinateViewModel(
        latitude = 52.279673567044476,
        longitude = -1.585226879165293,
    ),
    officialUrl = "https://www.warwick-castle.com/",
    googleCid = 2979592776009511813,
)
