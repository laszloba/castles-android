package com.vaslufi.castles.error

sealed class CastlesError(
    override val message: String,
    cause: Throwable? = null,
) : Exception(message, cause) {
    class MalformedUrlException(url: String) : CastlesError("Could not handle malformed URL: $url")

    class BrowserActivityNotFoundException(cause: Throwable? = null) :
        CastlesError("No default browser installed to open web page.", cause)
}
