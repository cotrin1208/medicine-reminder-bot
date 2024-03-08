package io.github.cotrin1208

import io.github.cotrin1208.plugin.configureKoin
import io.github.cotrin1208.plugin.configureSerialization
import io.github.cotrin1208.plugin.configureStatusPages
import io.github.cotrin1208.plugin.routing.configureRoute
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") { module() }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureStatusPages()
    configureRoute()
    configureKoin()
}
