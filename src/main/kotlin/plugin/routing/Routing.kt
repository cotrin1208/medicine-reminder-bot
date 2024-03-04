package io.github.cotrin1208.plugin.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRoute() {
    routing {
        lineBotRoute()
        pushMessageRoute()
    }
}
