package io.github.cotrin1208.model.message

import kotlinx.serialization.Serializable

@Serializable
data class QuickReplyItem(
    val type: String = "action",
    val imageUrl: String? = null,
    val action: Action,
)
