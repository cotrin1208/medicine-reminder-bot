package io.github.cotrin1208

import io.github.cotrin1208.plugin.configureKoin
import io.github.cotrin1208.plugin.configureSerialization
import io.github.cotrin1208.plugin.configureStatusPages
import io.github.cotrin1208.plugin.routing.configureRoute
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main(args: Array<String>) {
    embeddedServer(CIO, commandLineEnvironment(args)).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureStatusPages()
    configureRoute()
    configureKoin()
}
