package io.github.cotrin1208.model.webhook

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Source {
    val userId: String?

    @Serializable
    @SerialName("user")
    data class User(
        override val userId: String,
    ) : Source

    @Serializable
    @SerialName("group")
    data class Group(
        val groupId: String,
        override val userId: String?,
    ) : Source

    @Serializable
    @SerialName("room")
    data class Room(
        val roomId: String,
        override val userId: String?,
    ) : Source
}
