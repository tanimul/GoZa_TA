package com.tanimul.goza_ta.network.core

import androidx.annotation.Keep

@Keep
data class ErrorBody(
    var message: String?,
    var statusCode: Int?,
    var errors: String?
)
