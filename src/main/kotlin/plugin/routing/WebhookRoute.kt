package io.github.cotrin1208.plugin.routing

import io.github.cotrin1208.feature.LineSignatureVerification
import io.github.cotrin1208.model.webhook.Webhook
import io.github.cotrin1208.model.webhook.WebhookEvent
import io.github.cotrin1208.service.IWebhookService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import org.koin.ktor.ext.inject

fun Route.lineBotRoute() {
    val webhookHandleService: IWebhookService by inject()

    webhook("webhook") {
        call.respond(HttpStatusCode.OK)
        val requestBody = call.receive<Webhook>()
        requestBody.events.forEach {
            when (it) {
                is WebhookEvent.Follow -> webhookHandleService.onFollowEvent(it)
                is WebhookEvent.Postback -> webhookHandleService.onPostbackEvent(it)
            }
        }
    }
}

fun Route.webhook(
    path: String = "callback",
    body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit,
) {
    route(path) {
        install(DoubleReceive)
        install(LineSignatureVerification) {
            channelSecret = System.getenv("CHANNEL_SECRET")
        }
        post(body)
    }
}
