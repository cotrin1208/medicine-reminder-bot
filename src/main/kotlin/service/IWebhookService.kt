package io.github.cotrin1208.service

import io.github.cotrin1208.model.webhook.WebhookEvent

interface IWebhookService {
    suspend fun onFollowEvent(event: WebhookEvent.Follow)

    suspend fun onPostbackEvent(event: WebhookEvent.Postback)
}
