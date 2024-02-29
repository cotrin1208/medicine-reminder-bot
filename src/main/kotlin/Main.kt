package io.github.cotrin1208

import io.github.cotrin1208.plugin.configureKoin
import io.github.cotrin1208.plugin.configureSerialization
import io.github.cotrin1208.plugin.routing.configureRoute
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRoute()
    configureKoin()
}
