package io.github.cotrin1208

import io.github.cotrin1208.plugin.configureKoin
import io.github.cotrin1208.plugin.configureSerialization
import io.github.cotrin1208.plugin.configureStatusPages
import io.github.cotrin1208.plugin.routing.configureRoute
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    kotlinx.serialization.json.Json
    configureSerialization()
    configureStatusPages()
    configureRoute()
    configureKoin()
}
