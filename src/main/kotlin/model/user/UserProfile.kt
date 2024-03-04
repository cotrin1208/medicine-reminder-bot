package io.github.cotrin1208.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val displayName: String,
    val userId: String,
    val language: String? = null,
    val pictureUrl: String? = null,
    val statusMessage: String? = null,
)
