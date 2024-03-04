package io.github.cotrin1208.repository

import io.github.cotrin1208.model.error.ErrorResponse
import io.github.cotrin1208.model.error.LineApiException
import io.github.cotrin1208.model.message.PushMessage
import io.github.cotrin1208.model.user.UserProfile
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class LineApiRepository : ILineApiRepository {
    private val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
            })
        }
        install(DefaultRequest) {
            url("https://api.line.me/v2/bot/")
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(System.getenv("CHANNEL_ACCESS_TOKEN") ?: "", "")
                }
            }
        }
    }

    override suspend fun getUserProfile(userId: String): UserProfile {
        val response = client.get("profile/$userId")
        return when (response.status) {
            HttpStatusCode.OK -> response.body<UserProfile>()
            else -> throw LineApiException(response.body<ErrorResponse>().message)
        }
    }

    override suspend fun sendPushMessage(message: PushMessage) {
        val response = client.post("message/push") {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(message)
        }
        if (response.status != HttpStatusCode.OK) {
            println(response.body<ErrorResponse>().toString())
        }
    }
}
