package com.vaslufi.castles.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaslufi.castles.ui.theme.CastlesTheme

@Composable
fun Loading() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp)
        )
    }
}

@Preview(
    name = "Loading view - Light mode",
    showBackground = true,
)
@Preview(
    name = "Loading view - Dark mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun LoadingPreview() {
    CastlesTheme {
        Loading()
    }
}
