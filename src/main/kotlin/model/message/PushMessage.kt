package io.github.cotrin1208.model.message

import kotlinx.serialization.Serializable

@Serializable
data class PushMessage(
    val to: String,
    val messages: List<Message>,
    val notificationDisabled: Boolean? = null,
)
