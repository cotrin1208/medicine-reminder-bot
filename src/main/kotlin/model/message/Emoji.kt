package io.github.cotrin1208.model.message

import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val index: Int? = null,
    val productId: String? = null,
    val emojiId: String? = null,
    val quoteToken: String? = null,
)
