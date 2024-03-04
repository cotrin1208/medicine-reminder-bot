package io.github.cotrin1208.plugin

import com.google.cloud.datastore.DatastoreException
import io.github.cotrin1208.model.error.LineApiException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<DatastoreException> { call, _ ->
            call.respond(HttpStatusCode.InternalServerError)
        }
        exception<LineApiException> { call, _ ->
            call.respond(HttpStatusCode.BadGateway)
        }
    }
}
