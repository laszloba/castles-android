package com.vaslufi.castles.service.impl

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import com.vaslufi.castles.common.Result
import com.vaslufi.castles.common.Success
import com.vaslufi.castles.error.CastlesError
import com.vaslufi.castles.service.PlatformService
import timber.log.Timber

class PlatformServiceImpl(
    private val context: Context,
) : PlatformService {
    override fun openUrl(url: String): Result<Unit> =
        try {
            if (Patterns.WEB_URL.matcher(url).matches()) {
                val openWebPageIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    .apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                context.startActivity(openWebPageIntent)
                Success()
            } else {
                Result.Failure(CastlesError.MalformedUrlException(url))
            }
        } catch (e: ActivityNotFoundException) {
            Timber.w("No Activity found that can open this URL: $url")
            Result.Failure(CastlesError.BrowserActivityNotFoundException(e))
        }
}
