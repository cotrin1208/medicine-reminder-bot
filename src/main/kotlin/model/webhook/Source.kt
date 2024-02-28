package io.github.cotrin1208.model.webhook

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Source {
    @Serializable
    @SerialName("user")
    data class User(
        val userId: String,
    ) : Source

    @Serializable
    @SerialName("group")
    data class Group(
        val groupId: String,
        val userId: String?,
    ) : Source

    @Serializable
    @SerialName("room")
    data class Room(
        val roomId: String,
        val userId: String?,
    ) : Source
}
