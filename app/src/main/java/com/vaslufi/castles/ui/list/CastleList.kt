package com.vaslufi.castles.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vaslufi.castles.R
import com.vaslufi.castles.model.CastleListItemViewModel
import com.vaslufi.castles.ui.common.Loading
import com.vaslufi.castles.ui.theme.CastlesTheme
import com.vaslufi.castles.ui.theme.surfaceMain

@Composable
fun CastleList(
    viewModel: CastleListViewModel,
) {
    val loading by viewModel.loading.collectAsState()
    val castleList by viewModel.castleList.collectAsState()

    CastleList(
        loading = loading,
        items = castleList,
        onItemClick = viewModel::openDetails,
    )
}

@Composable
private fun CastleList(
    loading: Boolean,
    items: List<CastleListItemViewModel>?,
    onItemClick: (Long) -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.surfaceMain,
    ) {
        if (loading) {
            Loading()
        } else {
            // TODO Show errors
            items?.let { castles ->
                CastleList(
                    items = castles,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}

@Composable
private fun CastleList(
    items: List<CastleListItemViewModel>,
    onItemClick: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(0.dp, 24.dp, 0.dp, 0.dp),
    ) {
        items(
            items = items,
            key = { item -> item.id },
        ) { item ->
            CastleListItem(
                name = item.name,
                imageUrl = item.imageUrl,
                onClick = { onItemClick(item.id) },
            )
        }
    }
}

@Composable
private fun CastleListItem(
    name: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(20.dp, 0.dp, 20.dp, 22.dp)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        // TODO Use a custom placeholder
                        placeholder(R.mipmap.ic_launcher)
                        crossfade(true)
                    }
                ),
                contentDescription = stringResource(R.string.content_description_image_of_the_castle),
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = name,
                modifier = Modifier
                    .padding(12.dp, 8.dp, 8.dp, 8.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Preview(
    name = "List loaded",
    group = "loaded",
    showSystemUi = true,
)
@Composable
private fun CastleListPreview() {
    CastlesTheme {
        CastleList(
            loading = false,
            items = sampleCastleListData,
            onItemClick = {},
        )
    }
}

@Preview(
    name = "List loading",
    group = "loading",
    showSystemUi = true,
)
@Composable
private fun CastleListLoadingPreview() {
    CastlesTheme {
        CastleList(
            loading = true,
            items = null,
            onItemClick = {},
        )
    }
}

@Preview
@Composable
private fun CastleListItemPreview() {
    CastlesTheme {
        CastleListItem(
            name = sampleCastleListData[0].name,
            imageUrl = sampleCastleListData[0].imageUrl,
            onClick = {},
        )
    }
}

private val sampleCastleListData = listOf(
    CastleListItemViewModel(
        id = 1,
        name = "Arundel Castle",
        imageUrl = "https://image.url",
    ),
    CastleListItemViewModel(
        id = 2,
        name = "Bodiam Castle",
        imageUrl = "https://image.url",
    ),
    CastleListItemViewModel(
        id = 3,
        name = "Hever Castle",
        imageUrl = "https://image.url",
    ),
    CastleListItemViewModel(
        id = 4,
        name = "Warwick Castle",
        imageUrl = "https://image.url",
    )
)
