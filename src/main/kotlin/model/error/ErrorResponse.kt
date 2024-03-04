package io.github.cotrin1208.model.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
    val details: List<Detail>? = null,
)

@Serializable
data class Detail(
    val message: String? = null,
    val property: String? = null,
)
