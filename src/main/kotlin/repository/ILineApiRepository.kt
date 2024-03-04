package io.github.cotrin1208.repository

import io.github.cotrin1208.model.message.PushMessage
import io.github.cotrin1208.model.user.UserProfile

interface ILineApiRepository {
    suspend fun getUserProfile(userId: String): UserProfile

    suspend fun sendPushMessage(message: PushMessage)
}
