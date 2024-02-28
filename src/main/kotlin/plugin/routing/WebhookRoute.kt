package io.github.cotrin1208.plugin.routing

import io.github.cotrin1208.model.feature.LineSignatureVerification
import io.github.cotrin1208.model.webhook.Source
import io.github.cotrin1208.model.webhook.Webhook
import io.github.cotrin1208.model.webhook.WebhookEvent
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Application.lineBotRoute() {
    routing {
        webhook("webhook") {
            val requestBody = call.receive<Webhook>()
            for (event in requestBody.events) {
                when (event) {
                    is WebhookEvent.Follow -> {
                        when (val source = event.source) {
                            is Source.Group -> TODO()
                            is Source.Room -> TODO()
                            is Source.User -> println(source.userId)
                        }
                    }
                }
            }
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.webhook(
    path: String = "callback",
    body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit,
) {
    install(DoubleReceive)
    install(LineSignatureVerification) {
        channelSecret = System.getenv("CHANNEL_SECRET")
    }
    post(path, body)
}
