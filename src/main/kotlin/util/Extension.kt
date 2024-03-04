package io.github.cotrin1208.util

import io.ktor.http.*

val HttpHeaders.LineSignature: String
    get() = "X-Line-Signature"

fun <T> T.asList() = listOf(this)
