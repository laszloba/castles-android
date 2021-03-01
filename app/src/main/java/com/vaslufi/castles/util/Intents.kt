package com.vaslufi.castles.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object Intents {

    fun openUrl(context: Context, url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
